package lab.first.dao;

import lab.first.model.Route;

import java.util.List;

public interface DAO {

    public void addRoute(Route route);

    public  void updateRoute(Route route);

    public void removeRoute(int id);

    public Route getRouteById(int id);

    public List<Route> listRoutes();
}
