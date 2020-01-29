package lab.first.dao;

import java.util.List;

public interface DAO<T> {

    public void add(T t);

    public void remove(T t);

    public List<T> getList();
}
