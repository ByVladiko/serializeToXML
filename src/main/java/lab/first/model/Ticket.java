package lab.first.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@XmlRootElement(name = "ticket")
public class Ticket implements Serializable {

    private UUID id;
    private Airship airship;
    private Route route;

    public Ticket() {
    }

    public Ticket(Airship airship, Route route) {
        this.id = UUID.randomUUID();
        this.airship = airship;
        this.route = route;
    }

    public Ticket(UUID id, Airship airship, Route route) {
        this.id = id;
        this.airship = airship;
        this.route = route;
    }

    @XmlAttribute
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @XmlElement
    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @XmlElement
    public Airship getAirship() {
        return airship;
    }

    public void setAirship(Airship airship) {
        this.airship = airship;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id &&
                Objects.equals(airship, ticket.airship) &&
                Objects.equals(route, ticket.route);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (airship == null ? 0 : airship.hashCode());
        result = 31 * result + (route == null ? 0 : route.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", airship=" + airship +
                ", route=" + route +
                '}';
    }
}
