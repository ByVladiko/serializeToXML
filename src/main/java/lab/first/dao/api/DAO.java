package lab.first.dao.api;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    //read
    List<T> getAll();

    Optional<T> getId(long id);

    void save(T t);

    void update(T t, String[]params);

    void delete(T t);
}
