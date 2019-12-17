package lab.first.controllers.FXModels;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lab.first.model.Airship;

import java.util.UUID;

public class AirshipFX {
    private UUID id;
    private StringProperty model;
    private LongProperty numberOfSeat;

    public AirshipFX(UUID id, StringProperty model, LongProperty numberOfSeat) {
        this.id = id;
        this.model = model;
        this.numberOfSeat = numberOfSeat;
    }

    public AirshipFX(Airship airship) {
        id = airship.getId();
        model = new SimpleStringProperty(airship.getModel());
        numberOfSeat = new SimpleLongProperty(airship.getNumberOfSeat());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getModel() {
        return model.get();
    }

    public StringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public long getNumberOfSeat() {
        return numberOfSeat.get();
    }

    public LongProperty numberOfSeatProperty() {
        return numberOfSeat;
    }

    public void setNumberOfSeat(long numberOfSeat) {
        this.numberOfSeat.set(numberOfSeat);
    }
}
