package data;

import java.util.List;

public interface UserDataInterface<T> {

	public List<T> findAll();
	
	public T findBy(T t);
	public T findById(int id);
	public boolean find(T t);
	public boolean create(T t);
	public boolean update(T t);
	public boolean delete(T t);
	
}
