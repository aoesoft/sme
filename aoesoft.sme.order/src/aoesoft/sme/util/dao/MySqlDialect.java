package aoesoft.sme.util.dao;

import aoesoft.sme.util.dao.DaoManager.Mapper;
import aoesoft.sme.util.dao.DaoManager.Mapper.Column;

public class MySqlDialect implements Dialect{

	public String selectPage(int from, int size, Class<? extends AbsDao> target) {
		StringBuffer inner = new StringBuffer();
		Mapper mapper = DaoManager.get(target);
		String tableName = mapper.getTableName();
		inner.append("SELECT ");
		for (int i = 0; i < mapper.getColumns().size(); i++) {
			Column c = mapper.getColumns().get(i);
			inner.append(c.getColumnName());

			if (i < mapper.getColumns().size() - 1)
				inner.append(",");
		}
		inner.append(" FROM ").append(tableName);
		
		StringBuffer sql = new StringBuffer();
		sql.append(inner).append(" LIMIT ").append(from).append(",").append(size);
		
		System.out.println(sql.toString());
		return sql.toString();
	}

}
