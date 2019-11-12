package com.company;

import java.util.Objects;

public class Airship {
    private long ID;
    private String model;
    private long numberOfSeat;
    private static long idCount = 0;

    public Airship(String model, long numberOfSeat) {
        this.model = model;
        this.numberOfSeat = numberOfSeat;
        this.ID = idCount++;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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
                "ID=" + ID +
                ", model='" + model + '\'' +
                ", numberOfSeat=" + numberOfSeat +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airship)) return false;
        Airship airship = (Airship) o;
        return ID == airship.ID &&
                numberOfSeat == airship.numberOfSeat &&
                Objects.equals(model, airship.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, model, numberOfSeat);
    }
}
