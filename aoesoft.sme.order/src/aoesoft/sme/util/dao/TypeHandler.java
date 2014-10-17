package aoesoft.sme.util.dao;

import java.lang.reflect.Type;

public interface TypeHandler{
	/**
	 * 支持的数据库类型.
	 * @return
	 */
	String getJdbcType();
	
	Type getReturnType();
	
	/**
	 * 数据处理.
	 * @param javaType
	 * @param source
	 * @return
	 */
	Object handle(String javaType, Object source);
}
