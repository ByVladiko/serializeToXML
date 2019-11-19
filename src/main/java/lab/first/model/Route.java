package lab.first.model;

import java.util.Objects;

public class Route {
    private long id;
    private String startPoint; //pointOfDeparture
    private String endPoint;   //pointOfArrival

    private static long idCounter = 0;

    public Route(String startPoint, String endPoint){
        this.id=idCounter++;
        this.startPoint=startPoint;
        this.endPoint=endPoint;
        }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", startPoint='" + startPoint + '\'' +
                ", endPoint='" + endPoint + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;
        Route route = (Route) o;
        return id == route.id &&
                Objects.equals(startPoint, route.startPoint) &&
                Objects.equals(endPoint, route.endPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startPoint, endPoint);
    }
}

