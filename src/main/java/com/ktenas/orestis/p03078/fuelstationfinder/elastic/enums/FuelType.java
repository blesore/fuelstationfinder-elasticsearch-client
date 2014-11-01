package com.ktenas.orestis.p03078.fuelstationfinder.elastic.enums;

public enum FuelType {

    UNLEADED_95("Unleaded 95"),
    UNLEADED_100("Unleaded 100"),
    DIESEL("Diesel"),
    AUTOGAS("Autogas");

    private final String title;

    private FuelType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
