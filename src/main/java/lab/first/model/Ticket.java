package lab.first.model;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Ticket {
    private UUID id;
    private Airship airship;
    private List<Route> routeList;

    public Ticket(Airship airship, List<Route> routeList) {
        this.id = UUID.randomUUID();
        this.airship = airship;
        this.routeList = routeList;
    }

    public Ticket(UUID id, Airship airship, List<Route> routeList) {
        this.id = id;
        this.airship = airship;
        this.routeList = routeList;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Airship getAirship() {
        return airship;
    }

    public void setAirship(Airship airship) {
        this.airship = airship;
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public void setTickets(List<Route> routeList) {
        this.routeList = routeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id &&
                Objects.equals(airship, ticket.airship) &&
                Objects.equals(routeList, ticket.routeList);
    }

    @Override
    public int hashCode() {
        int result = 17;
        for (int i = 0; i < routeList.size(); i++) {
            result = 31 * result + routeList.hashCode();
        }
        return airship.hashCode() + result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", airship=" + airship +
                ", routeList=" + routeList +
                '}';
    }
}
