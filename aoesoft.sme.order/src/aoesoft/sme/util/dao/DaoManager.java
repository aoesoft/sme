package aoesoft.sme.util.dao;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import aoesoft.sme.util.annotation.Key;
import aoesoft.sme.util.annotation.Table;

/**
 * 数据库层管理器.
 * @author tangxiucai
 *
 */
public class DaoManager {
	/**
	 * 字段对表列映射.
	 */
	private static Map<Class<? extends AbsDao>, Mapper> mapper
		= new HashMap<Class<? extends AbsDao>, Mapper>(); //key:RealClass
	/**
	 * 方言.
	 */
	private static Dialect dialect = null;
	/**
	 * 类型处理器.
	 */
	private static List<TypeHandler> typeHandlers = null;
	
	static{
		TypeHandler intTypeHandler = new IntTypeHandler();
		
		
		registeTypeHandler(intTypeHandler);
	}
	
	private DaoManager(){
	}
	
	public static void setDialect(Dialect d){
		dialect = d;
	}
	
	public static Dialect getDialect(){
		return dialect;
	}
	
	public static void registeTypeHandler(TypeHandler handler){
		if(typeHandlers == null)
			typeHandlers = new LinkedList<TypeHandler>();
		
		typeHandlers.add(handler);
	}
	
	public static TypeHandler[] getTypeHandlers(){
		if(typeHandlers == null)
			return new TypeHandler[0];
		return typeHandlers.toArray(new TypeHandler[typeHandlers.size()]);
	}
	
	public static TypeHandler getTypeHandler(String jdbcType){
		for(TypeHandler h : getTypeHandlers()){
			if(h.getJdbcType().equalsIgnoreCase(jdbcType))
				return h;
		}
		return null;
	}
	
	static class Mapper{
		private String className;
		private String tableName;
		private List<Column> columns; //columns include keys.
		private List<Column> keys;
		
		public String getClassName() {
			return className;
		}
		
		public String getTableName() {
			return tableName;
		}

		public List<Column> getColumns() {
			return columns;
		}

		public List<Column> getKeys() {
			return keys;
		}


		static class Column{
			private String jdbcType;
			private String javaType;
			private String fieldName;
			private String columnName;
			public String getJdbcType() {
				return jdbcType;
			}
			public String getJavaType() {
				return javaType;
			}
			public String getFieldName() {
				return fieldName;
			}
			public String getColumnName() {
				return columnName;
			}
		}
	}
	
	public static void add(Class<? extends AbsDao> clazz){
		if(clazz.isAnnotationPresent(Table.class)){
			Mapper m = new Mapper();
			m.tableName = ((Table)clazz.getAnnotation(Table.class)).value();
			m.className = clazz.getName();
			m.columns = new LinkedList<Mapper.Column>();
			m.keys = new LinkedList<Mapper.Column>();
					
			Field[] fields = clazz.getDeclaredFields();
			for(Field f : fields){
				if(f.isAnnotationPresent(aoesoft.sme.util.annotation.Column.class)){
					aoesoft.sme.util.annotation.Column c = f.getAnnotation(aoesoft.sme.util.annotation.Column.class);
					Mapper.Column col = new Mapper.Column();
					col.fieldName = f.getName();
					col.columnName = c.value();
					col.javaType = f.getType().getName();
					col.jdbcType = c.type();
					
					m.columns.add(col);
				}
				
				if(f.isAnnotationPresent(Key.class)){
					for(Mapper.Column col : m.columns){
						if(col.fieldName.equals(f.getName())){
							m.keys.add(col);
							break;
						}
					}
				}
			}
			
			mapper.put(clazz, m);
		}
	}
	
	public static boolean contain(Class<? extends AbsDao> clazz){
		return mapper.containsKey(clazz);
	}
	
	public static Mapper get(Class<? extends AbsDao> clazz){
		return mapper.get(clazz);
	}
}
