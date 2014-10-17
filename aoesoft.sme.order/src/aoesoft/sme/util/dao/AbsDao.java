package aoesoft.sme.util.dao;

import java.lang.reflect.Method;
import java.util.List;

import aoesoft.sme.util.dao.DaoManager.Mapper;
import aoesoft.sme.util.dao.DaoManager.Mapper.Column;

public class AbsDao<T> implements Dao<T> {
	public AbsDao() {
		DaoManager.add(this.getClass());
	}

	public T[] selectAll() {
		StringBuffer sql = new StringBuffer();
		Mapper mapper = DaoManager.get(this.getClass());
		String tableName = mapper.getTableName();
		sql.append("SELECT ");
		for (int i = 0; i < mapper.getColumns().size(); i++) {
			Column c = mapper.getColumns().get(i);
			sql.append(c.getColumnName());

			if (i < mapper.getColumns().size() - 1)
				sql.append(",");
		}
		sql.append(" FROM ").append(tableName);
		System.out.println(sql);
		//TODO mapping to bean.
		
		return null;
	}

	public T[] selectPage(int fromIndex, int size) {
		String sql = null;
		if(DaoManager.getDialect() != null)
			sql = DaoManager.getDialect().selectPage(fromIndex, size, this.getClass());
		
		return null;
	}

	public boolean add() {
		StringBuffer sql = new StringBuffer();
		Mapper mapper = DaoManager.get(this.getClass());
		String tableName = mapper.getTableName();
		sql.append("INSERT INTO ").append(tableName).append(" (");
		for (int i = 0; i < mapper.getColumns().size(); i++) {
			Column c = mapper.getColumns().get(i);
			sql.append(c.getColumnName());

			if (i < mapper.getColumns().size() - 1)
				sql.append(",");
		}
		sql.append(") values(");
		for (int i = 0; i < mapper.getColumns().size(); i++) {
			Column c = mapper.getColumns().get(i);
			Object result = invokeMethod(c.getFieldName());
			sql.append("'").append(result).append("'");

			if (i < mapper.getColumns().size() - 1)
				sql.append(",");
		}
		sql.append(")");

		System.out.println(sql.toString());
		return true;
	}

	public boolean delete() {
		StringBuffer sql = new StringBuffer();
		Mapper mapper = DaoManager.get(this.getClass());
		String tableName = mapper.getTableName();
		sql.append("DELETE FROM ").append(tableName);
		
		List<Column> keys = mapper.getKeys();
		if(keys != null && keys.size() > 0){
			sql.append(" WHERE ");
			for(int i=0; i<keys.size(); i++){
				Column c = keys.get(i);
				
				Object result  = invokeMethod(c.getFieldName());
				/*TypeHandler h = DaoManager.getTypeHandler(c.getJdbcType());
				if( h != null){
					result = h.handle(c.getJavaType(), result);
				}*/
				sql.append(c.getColumnName()).append("= '").append(result).append("' ");
				
				if(i<keys.size()-1)
					sql.append(" AND ");
			}
		}
		System.out.println(sql.toString());
		
		return false;
	}

	public boolean update() {
		return false;
	}

	private static String upperFirstChar(String target) {
		if (target == null)
			return target;
		StringBuffer sb = new StringBuffer(target.toLowerCase());
		sb = sb.replace(0, 1, (String.valueOf(sb.charAt(0)).toUpperCase()));
		return sb.toString();
	}
	
	public int deleteAll() {
		StringBuffer sql = new StringBuffer();
		Mapper mapper = DaoManager.get(this.getClass());
		String tableName = mapper.getTableName();
		sql.append("DELETE FROM ").append(tableName);
		System.out.println(sql.toString());
		return 0;
	}

	public int batchDelete(T...t) {
		StringBuffer sql = new StringBuffer();
		Mapper mapper = DaoManager.get(this.getClass());
		String tableName = mapper.getTableName();
		sql.append("DELETE FROM ").append(tableName);
		List<Column> keys = mapper.getKeys();
		if(keys != null && keys.size() > 0){
			sql.append(" WHERE ");
			
			if(keys.size() == 1) {//单个key
				Column c = keys.get(0);
				sql.append(c.getColumnName()).append(" IN (");
				for(int i=0; i<t.length; i++){
					Object result  = invokeMethod(t[i], c.getFieldName());
					sql.append("'").append(result).append("'");
					
					if(i<t.length-1)
						sql.append(",");
				}
				sql.append(")");
			}else{
				for(int j=0; j<t.length; j++){
					sql.append("(");
					for(int i=0; i<keys.size(); i++){
						Column c = keys.get(i);
						Object result  = invokeMethod(t[j], c.getFieldName());
						sql.append(c.getColumnName()).append("= '").append(result).append("' ");
						
						if(i<keys.size()-1)
							sql.append(" AND ");
					}
					sql.append(")");
					if(j<t.length -1)
						sql.append(" OR ");
				}
			}
			
		}
		System.out.println(sql.toString());
		return 0;
	}
	
	private Object invokeMethod(String fieldName){
		return invokeMethod(this, fieldName);
	}

	private Object invokeMethod(Object target, String fieldName) {
		try {
			String methodName = "get" + upperFirstChar(fieldName);
			Method m = this.getClass().getDeclaredMethod(methodName, null);
			if (m == null) {
				methodName = "is" + upperFirstChar(fieldName);
				m = this.getClass().getDeclaredMethod(methodName, null);
			}
			
			Object result = m.invoke(target, null);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
