package lab.first.model;

import java.util.Objects;
import java.util.UUID;

public class Route {
    private UUID id;
    private String startPoint; //pointOfDeparture
    private String endPoint;   //pointOfArrival


    public Route(String startPoint, String endPoint){
        this.id = UUID.randomUUID();
        this.startPoint=startPoint;
        this.endPoint=endPoint;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Route{" +
                "id=" + id +
                ", startPoint='" + startPoint + '\'' +
                ", endPoint='" + endPoint + '\'' +
                '}';
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Route)) return false;
        if (!super.equals(object)) return false;
        Route route = (Route) object;
        return java.util.Objects.equals(id, route.id) &&
                java.util.Objects.equals(startPoint, route.startPoint) &&
                java.util.Objects.equals(endPoint, route.endPoint);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), id, startPoint, endPoint);
    }
}
