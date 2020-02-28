package lab.first.dao.factory;

import airship.dao.DAO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FactoryDAO extends Remote {

    public DAO getAirshipDAO() throws RemoteException;

    public DAO getClientDAO() throws RemoteException;

    public DAO getRouteDAO() throws RemoteException;

    public DAO getTicketDAO() throws RemoteException;

}
