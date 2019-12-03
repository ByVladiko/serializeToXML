package lab.first.dao.impl;

import lab.first.dao.api.DAO;
import lab.first.model.Route;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class RouteDAO implements DAO<Route> {

    private List<Route> routes = new ArrayList<>();

    public RouteDAO(){
        routes.add(new Route("Toljatti","Samara"));
        routes.add(new Route("Samara","Toljatti"));
        routes.add(new Route("Moskow","Samara"));
        routes.add(new Route("Samara","Moskow"));
    }

    public Optional<Route> getId(long id){
        return Optional.ofNullable(routes.get((int) id));
    }

    public List<Route> getAll() {
        return routes;
    }

    public void save(Route route) {
        routes.add(route);
    }

    public void update(Route route, String[]params) {
        route.setStartPoint(Objects.requireNonNull(
                params[0], "Start point cannot be null"));
        route.setEndPoint(Objects.requireNonNull(
                params[1], "End point cannot be null"));
        routes.add(route);
    }

    public void delete(Route route) {
        routes.remove(route);
    }
}
