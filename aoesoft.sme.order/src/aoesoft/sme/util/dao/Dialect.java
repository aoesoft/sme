package aoesoft.sme.util.dao;

public interface Dialect {
	String selectPage(int from, int size, Class<? extends AbsDao> target);
}
