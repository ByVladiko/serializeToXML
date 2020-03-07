package lab.first.serialize;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Xml<T> extends Remote {

    List<T> read() throws RemoteException;

    boolean save(T obj) throws RemoteException;

    boolean delete(T obj) throws RemoteException;
}
