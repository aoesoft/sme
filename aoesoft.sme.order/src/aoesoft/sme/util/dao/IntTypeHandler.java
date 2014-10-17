package aoesoft.sme.util.dao;

import java.lang.reflect.Type;

public class IntTypeHandler implements TypeHandler{

	public String getJdbcType() {
		return "int";
	}

	public Integer handle(String javaType, Object source) {
		if("java.lang.String".equals(javaType) || "int".equals(javaType)){
			return Integer.parseInt((String) source);
		}
		return 0;
	}

	public Type getReturnType() {
		return int.class;
	}
}
