package lab.first.view.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lab.first.model.Route;

import java.util.UUID;

public class RouteFX {
    private UUID id;
    private StringProperty startPoint;
    private StringProperty endPoint;

    public RouteFX(UUID id, StringProperty startPoint, StringProperty endPoint) {
        this.id = id;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public RouteFX(Route route) {
        id = route.getId();
        startPoint = new SimpleStringProperty(route.getStartPoint());
        endPoint = new SimpleStringProperty(route.getEndPoint());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStartPoint() {
        return startPoint.get();
    }

    public StringProperty startPointProperty() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint.set(startPoint);
    }

    public String getEndPoint() {
        return endPoint.get();
    }

    public StringProperty endPointProperty() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint.set(endPoint);
    }
}
