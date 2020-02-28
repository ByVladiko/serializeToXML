package lab.first.dao.factory;

import airship.dao.DAO;
import airship.dao.FactoryDAO;
import lab.first.dao.DAOImpl.AirshipDAOImpl;
import lab.first.dao.DAOImpl.ClientDAOImpl;
import lab.first.dao.DAOImpl.RouteDAOImpl;
import lab.first.dao.DAOImpl.TicketDAOImpl;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FactoryDAOImpl extends UnicastRemoteObject implements FactoryDAO, Serializable {

    private static final long serialVersionUID = 1L;

    private static FactoryDAO factoryDAO;

    public FactoryDAOImpl() throws RemoteException {
        super();
    }

    public static FactoryDAO getInstance() {
            try {
                if (factoryDAO == null) {
                factoryDAO = new FactoryDAOImpl();
                    return factoryDAO;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        return factoryDAO;
    }

    @Override
    public DAO getAirshipDAO() throws RemoteException {
        return (DAO) AirshipDAOImpl.getInstance();
    }

    @Override
    public DAO getClientDAO() throws RemoteException {
        return (DAO) ClientDAOImpl.getInstance();
    }

    @Override
    public DAO getRouteDAO() throws RemoteException {
        return (DAO) RouteDAOImpl.getInstance();
    }

    @Override
    public DAO getTicketDAO() throws RemoteException {
        return (DAO) TicketDAOImpl.getInstance();
    }
}
