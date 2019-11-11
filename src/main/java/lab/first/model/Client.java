package lab.first.model;

import java.util.ArrayList;

public class Client {
    private long ID;
    private String name;
    private String surname;
    private String secondName;
    private ArrayList<Ticket> tickets;

    private static long idCounter = 0;

    public Client(String name, String surname, String secondName) {
        this.name = name;
        this.surname = surname;
        this.secondName = secondName;
        this.ID = idCounter++;
        this.tickets = new ArrayList<>();
    }

    public Client(String name, String surname, String secondName, ArrayList<Ticket> tickets) {
        this.name = name;
        this.surname = surname;
        this.secondName = secondName;
        this.tickets = tickets;
    }

    public Client() {
        this.name = "Undefined";
        this.surname = "Undefined";
        this.secondName = "Undefined";
        this.tickets = new ArrayList<>();
    }

    public long getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Client{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", secondName='" + secondName + '\'' +
                ", tickets=" + tickets +
                '}';
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (name == null ? 0 : name.hashCode());
        result = 31 * result + (surname == null ? 0 : surname.hashCode());
        result = 31 * result + (secondName == null ? 0 : secondName.hashCode());
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
        return name.equals(client.getName()) && surname.equals(client.getSurname()) && secondName.equals(client.getSecondName());
    }
}
