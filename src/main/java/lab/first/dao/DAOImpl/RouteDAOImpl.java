package lab.first.dao.DAOImpl;

import airship.model.Route;
import lab.first.dao.DAO;
import lab.first.serialize.RouteXmlImpl;
import lab.first.serialize.Xml;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

public class RouteDAOImpl implements DAO<Route>, Serializable {

    private static final long serialVersionUID = 1L;

    private Xml xml = new RouteXmlImpl();

    private static DAO routeDAO;

    public static DAO getInstance() {
        if (routeDAO == null) {
            routeDAO = new RouteDAOImpl();
            return routeDAO;
        }
        return routeDAO;
    }

    @Override
    public void add(Route route) throws RemoteException {
        xml.save(route);
    }

    @Override
    public void remove(Route route) throws RemoteException  {
        xml.delete(route);
    }

    @Override
    public List<Route> getList() throws RemoteException  {
        return xml.read();
    }
}
