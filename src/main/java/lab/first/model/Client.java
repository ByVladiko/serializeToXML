package lab.first.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@XmlRootElement(name = "Client")
public class Client {
    private UUID id;
    private String firstName;
    private String middleName;
    private String lastName;
    private List<Ticket> tickets;

    public Client(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.id = UUID.randomUUID();
        this.tickets = new ArrayList<Ticket>();
    }

    public Client(String firstName, String middleName, String lastName, List<Ticket> tickets) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.tickets = tickets;
    }

    public Client(UUID id, String firstName, String middleName, String lastName, List<Ticket> tickets) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.tickets = tickets;
    }

    public Client() {
        this.firstName = "Undefined";
        this.middleName = "Undefined";
        this.lastName = "Undefined";
        this.tickets = new ArrayList<Ticket>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Client{" +
                "ID=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", tickets=" + tickets +
                '}';
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (firstName == null ? 0 : firstName.hashCode());
        result = 31 * result + (middleName == null ? 0 : middleName.hashCode());
        result = 31 * result + (lastName == null ? 0 : lastName.hashCode());
        for (int i = 0; i < tickets.size(); i++) {
            result = 31 * result + tickets.hashCode();
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Client)) {
            return false;
        }
        Client client = (Client) obj;
        for (int i = 0; i < tickets.size(); i++) {
            if(!tickets.get(i).equals(client.getTickets().get(i))) {
                return false;
            }
        }
        return firstName.equals(client.getFirstName()) && middleName.equals(client.getMiddleName()) && lastName.equals(client.getLastName());
    }
}
