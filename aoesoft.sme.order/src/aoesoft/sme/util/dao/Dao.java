package aoesoft.sme.util.dao;

public interface Dao<T> {
	T[] selectAll();
	T[] selectPage(int from, int size);
	boolean add();
	boolean delete();
	boolean update();
	
	int deleteAll();
	int batchDelete(T[] t);
}
