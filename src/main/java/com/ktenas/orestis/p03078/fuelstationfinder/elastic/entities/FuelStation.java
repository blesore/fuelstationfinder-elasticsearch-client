package com.ktenas.orestis.p03078.fuelstationfinder.elastic.entities;

import java.util.Date;
import java.util.List;

import com.ktenas.orestis.p03078.fuelstationfinder.elastic.enums.StationBrand;

public class FuelStation {

    private long stationCode;
    private String address;
    private String ownerName;
    private StationBrand brand;
    private double[] location;
    private List<Fuel> availableFuel;
    private Date lastUpdated;
    private boolean cheapestInRange = false;

    public FuelStation(long stationCode, String address, String ownerName,
            StationBrand brand, double[] location, double longitude,
            List<Fuel> availableFuel, Date lastUpdated) {
        this.stationCode = stationCode;
        this.address = address;
        this.ownerName = ownerName;
        this.brand = brand;
        this.location = location;
        this.availableFuel = availableFuel;
        this.lastUpdated = lastUpdated;
    }

    public FuelStation() {
    }

    public long getStationCode() {
        return stationCode;
    }

    public void setStationCode(long stationCode) {
        this.stationCode = stationCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public StationBrand getBrand() {
        return brand;
    }

    public void setBrand(StationBrand brand) {
        this.brand = brand;
    }

    public double[] getLocation() {
        return location;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    public List<Fuel> getAvailableFuel() {
        return availableFuel;
    }

    public void setAvailableFuel(List<Fuel> availableFuel) {
        this.availableFuel = availableFuel;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean isCheapestInRange() {
        return cheapestInRange;
    }

    public void setCheapestInRange(boolean cheapestInRange) {
        this.cheapestInRange = cheapestInRange;
    }
}
