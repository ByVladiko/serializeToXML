package lab.first.serialize;

import java.util.List;

public interface Xml<T> {

    List<T> read();

    void save(T obj);

    void delete(T obj);
}
