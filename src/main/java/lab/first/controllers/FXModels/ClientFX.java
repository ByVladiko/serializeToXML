package lab.first.controllers.FXModels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lab.first.model.Client;
import lab.first.model.Ticket;

import java.util.UUID;

public class ClientFX {
    private UUID id;
    private StringProperty firstName;
    private StringProperty middleName;
    private StringProperty lastName;
    private ObservableList<Ticket> tickets; //Здесь должно быть ObservableList<TicketFX>, но я не знаю как это реализовать

    public ClientFX(UUID id, StringProperty firstName, StringProperty middleName, StringProperty lastName, ObservableList<Ticket> tickets) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.tickets = tickets;
    }

    public ClientFX(Client client) {
        id = client.getId();
        firstName = new SimpleStringProperty(client.getFirstName());
        middleName = new SimpleStringProperty(client.getMiddleName());
        lastName = new SimpleStringProperty(client.getLastName());
        tickets = FXCollections.observableArrayList(client.getTickets());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getMiddleName() {
        return middleName.get();
    }

    public StringProperty middleNameProperty() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName.set(middleName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public ObservableList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ObservableList<Ticket> tickets) {
        this.tickets = tickets;
    }
}
