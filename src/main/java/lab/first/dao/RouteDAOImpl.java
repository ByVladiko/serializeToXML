package lab.first.dao;

import lab.first.model.Route;
import lab.first.serialize.RouteXmlImpl;

import java.util.List;

public class RouteDAOImpl implements DAO<Route> {

    private RouteXmlImpl xml = new RouteXmlImpl();

    @Override
    public void add(Route route) {
        xml.save(route);
    }

    @Override
    public void update(Route route) {

    }

    @Override
    public void remove(Route route) {
        xml.delete(route);
    }

    @Override
    public Route getById(String id) {
        return null;
    }

    @Override
    public List<Route> getList() {
        return xml.read();
    }
}
