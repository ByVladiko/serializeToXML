package lab.first.model;

import java.util.Objects;
import java.util.UUID;

public class Airship {
    private UUID id;
    private String model;
    private long seatNumber;

    public Airship(String model, long seatNumber) {
        this.model = model;
        this.seatNumber = seatNumber;
        this.id = UUID.randomUUID();
    }

    public UUID getID() {
        return id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(long seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "Airship{" +
                "id=" + id.toString() +
                ", model='" + model + '\'' +
                ", seatNumber=" + seatNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airship)) return false;
        Airship airship = (Airship) o;
        return id == airship.id &&
                seatNumber == airship.seatNumber &&
                Objects.equals(model, airship.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id.toString(), model, seatNumber);
    }
}
