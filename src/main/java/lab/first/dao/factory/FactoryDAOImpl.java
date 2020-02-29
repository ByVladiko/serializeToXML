package lab.first.dao.factory;

import airship.dao.DAO;
import airship.dao.FactoryDAO;
import airship.model.Airship;
import airship.model.Client;
import airship.model.Route;
import airship.model.Ticket;
import lab.first.dao.DAOImpl.AirshipDAOImpl;
import lab.first.dao.DAOImpl.ClientDAOImpl;
import lab.first.dao.DAOImpl.RouteDAOImpl;
import lab.first.dao.DAOImpl.TicketDAOImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FactoryDAOImpl extends UnicastRemoteObject implements FactoryDAO {

    private static FactoryDAO factoryDAO;

    private FactoryDAOImpl() throws RemoteException {
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
    public DAO<Airship> getAirshipDAO() throws RemoteException {
        return (DAO<Airship>) AirshipDAOImpl.getInstance();
    }

    @Override
    public DAO<Client> getClientDAO() throws RemoteException {
        return (DAO<Client>) ClientDAOImpl.getInstance();
    }

    @Override
    public DAO<Route> getRouteDAO() throws RemoteException {
        return (DAO<Route>) RouteDAOImpl.getInstance();
    }

    @Override
    public DAO<Ticket> getTicketDAO() throws RemoteException {
        return (DAO<Ticket>) TicketDAOImpl.getInstance();
    }
}
