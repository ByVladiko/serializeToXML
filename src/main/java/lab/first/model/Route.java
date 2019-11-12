package com.company;

import java.util.Objects;

public class Route {
    private long ID;
    private String pointOfDeparture;
    private String pointOfArrival;

    private static long idCounter = 0;

    public Route(String pointOfDeparture, String pointOfArrival){
        this.ID=idCounter++;
        this.pointOfDeparture=pointOfDeparture;
        this.pointOfArrival=pointOfArrival;
        }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getPointOfDeparture() {
        return pointOfDeparture;
    }

    public void setPointOfDeparture(String pointOfDeparture) {
        this.pointOfDeparture = pointOfDeparture;
    }

    public String getPointOfArrival() {
        return pointOfArrival;
    }

    public void setPointOfArrival(String pointOfArrival) {
        this.pointOfArrival = pointOfArrival;
    }

    @Override
    public String toString() {
        return "Route{" +
                "ID=" + ID +
                ", pointOfDeparture='" + pointOfDeparture + '\'' +
                ", pointOfArrival='" + pointOfArrival + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;
        Route route = (Route) o;
        return ID == route.ID &&
                Objects.equals(pointOfDeparture, route.pointOfDeparture) &&
                Objects.equals(pointOfArrival, route.pointOfArrival);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, pointOfDeparture, pointOfArrival);
    }
}

