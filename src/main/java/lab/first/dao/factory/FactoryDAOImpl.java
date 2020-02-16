package lab.first.dao.factory;

import lab.first.dao.*;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FactoryDAOImpl extends UnicastRemoteObject implements FactoryDAO, Serializable {

    private static FactoryDAOImpl factoryDAOImpl;

    public static FactoryDAOImpl getInstance() {
        if (factoryDAOImpl == null) {
            try {
                factoryDAOImpl = new FactoryDAOImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return factoryDAOImpl;
        }
        return factoryDAOImpl;
    }

    public FactoryDAOImpl() throws RemoteException {
        super();
    }

    public DAO getAirshipDAO() {
        return AirshipDAOImpl.getInstance();
    }

    public DAO getClientDAO() {
        return ClientDAOImpl.getInstance();
    }

    public DAO getRouteDAO() {
        return RouteDAOImpl.getInstance();
    }

    public DAO getTicketDAO() {
        return TicketDAOImpl.getInstance();
    }

}
