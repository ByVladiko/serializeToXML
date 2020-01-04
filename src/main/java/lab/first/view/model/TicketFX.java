package lab.first.view.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lab.first.view.ConverterToFX;
import lab.first.model.Route;
import lab.first.model.Ticket;

import java.util.UUID;

public class TicketFX {
    private UUID id;
    private AirshipFX airship;
    private ObservableList<Route> routeList; //Здесь должно быть ObservableList<RouteFX>, но я не знаю как это реализовать

    public TicketFX(UUID id, AirshipFX airship, ObservableList<Route> routeList) {
        this.id = id;
        this.airship = airship;
        this.routeList = routeList;
    }

    public TicketFX(Ticket ticket) {
        ConverterToFX converter = new ConverterToFX();
        id = ticket.getId();
        airship = converter.convertToFx(ticket).getAirship();
        routeList = FXCollections.observableArrayList(ticket.getRouteList());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public AirshipFX getAirship() {
        return airship;
    }

    public void setAirship(AirshipFX airship) {
        this.airship = airship;
    }

    public ObservableList<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(ObservableList<Route> routeList) {
        this.routeList = routeList;
    }
}
