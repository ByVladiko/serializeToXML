package lab.first.dao;

import lab.first.model.Route;
import java.util.List;

public interface RouteDAO{
    //create
    void add(Route route);

    //read
    List<Route> getAll();

    Route getId(Long id);

    //update
    void update(Route route);

    //delete
    void remove(Route route);
}