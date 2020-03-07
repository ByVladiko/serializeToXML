package lab.first.dao.DAOImpl;

import airship.dao.DAO;
import airship.model.Client;
import airship.model.Route;
import lab.first.serialize.RouteXmlImpl;
import lab.first.serialize.Xml;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RouteDAOImpl extends UnicastRemoteObject implements DAO<Route> {

    private Xml<Route> xml = new RouteXmlImpl();

    private static DAO<Route> routeDAO;

    private RouteDAOImpl() throws RemoteException {
    }

    public static DAO<Route> getInstance() {
        if (routeDAO == null) {
            try {
                routeDAO = new RouteDAOImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return routeDAO;
        }
        return routeDAO;
    }

    @Override
    public boolean add(Route route) throws RemoteException {
        return xml.save(route);
    }

    @Override
    public boolean remove(Route route) throws RemoteException  {
        return xml.delete(route);
    }

    @Override
    public List<Route> getList() throws RemoteException  {
        return xml.read();
    }
}
