package lab.first.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DAO<T> extends Remote {

    public void add(T t) throws RemoteException;

    public void remove(T t) throws RemoteException;

    public List<T> getList() throws RemoteException;
}
