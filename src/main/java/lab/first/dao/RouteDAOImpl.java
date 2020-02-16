package lab.first.dao;

import lab.first.model.Route;
import lab.first.serialize.RouteXmlImpl;

import java.io.Serializable;
import java.util.List;

public class RouteDAOImpl implements DAO<Route>, Serializable {

    private RouteXmlImpl xml;
    private static RouteDAOImpl routeDAO;

    public static RouteDAOImpl getInstance() {
        if (routeDAO == null) {
            routeDAO = new RouteDAOImpl();
            return routeDAO;
        }
        return routeDAO;
    }

    private RouteDAOImpl() {
        xml = new RouteXmlImpl();
    }

    @Override
    public void add(Route route) {
        xml.save(route);
    }

    @Override
    public void remove(Route route) {
        xml.delete(route);
    }

    @Override
    public List<Route> getList() {
        return xml.read();
    }
}
