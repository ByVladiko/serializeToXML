package lab.first.model;

import java.util.Objects;
import java.util.UUID;

public class Airship {
    private UUID id;
    private String model;
    private long numberOfSeat;

    public Airship(String model, long numberOfSeat) {
        this.model = model;
        this.numberOfSeat = numberOfSeat;
        this.id = UUID.randomUUID();
    }

    public Airship(UUID id, String model, long numberOfSeat) {
        this.id = id;
        this.model = model;
        this.numberOfSeat = numberOfSeat;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getNumberOfSeat() {
        return numberOfSeat;
    }

    public void setNumberOfSeat(long numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }

    @Override
    public String toString() {
        return "Airship{" +
                "ID=" + id +
                ", model='" + model + '\'' +
                ", numberOfSeat=" + numberOfSeat +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airship)) return false;
        Airship airship = (Airship) o;
        return id == airship.id &&
                numberOfSeat == airship.numberOfSeat &&
                Objects.equals(model, airship.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, numberOfSeat);
    }
}
