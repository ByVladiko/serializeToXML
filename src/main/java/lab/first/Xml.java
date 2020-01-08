package lab.first;

public interface Xml<T> {

    public T read();

    public void save(T obj);

    public void delete();

    public void update();
}
