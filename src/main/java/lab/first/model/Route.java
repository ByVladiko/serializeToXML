package lab.first.model;

import javax.xml.bind.annotation.*;
import java.util.Objects;
import java.util.UUID;

@XmlRootElement(name = "Route")
public class Route {
    private UUID id;
    private String startPoint; //pointOfDeparture
    private String endPoint;   //pointOfArrival


    public Route(String startPoint, String endPoint){
        this.id = UUID.randomUUID();
        this.startPoint=startPoint;
        this.endPoint=endPoint;
        }

    public Route() {
    }

    public Route(UUID id, String startPoint, String endPoint) {
        this.id = id;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @XmlAttribute
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    @XmlElement
    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }
    @XmlElement
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

