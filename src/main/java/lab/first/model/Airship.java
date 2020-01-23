package lab.first.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

@XmlRootElement(name = "airship")
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

    public Airship() {}

    @XmlAttribute
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @XmlElement
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @XmlElement
    public long getNumberOfSeat() {
        return numberOfSeat;
    }

    public void setNumberOfSeat(long numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }

    @Override
    public String toString() {
        return String.format("%s(%d)", model, numberOfSeat);
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
