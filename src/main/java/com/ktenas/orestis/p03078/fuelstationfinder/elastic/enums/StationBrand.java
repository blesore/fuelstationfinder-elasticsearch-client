package com.ktenas.orestis.p03078.fuelstationfinder.elastic.enums;

public enum StationBrand {

    AEGEAN("ΑΙΓΑΙΟ (AEGEAN)"),
    AVIN("AVIN"),
    BP("BP"),
    CYCLON("CYCLON"),
    EKO("EKO"),
    ELIN("ΕΛΙΝΟΙΛ"),
    ETEKA("ΕΤΕΚΑ"),
    JETOIL("JETOIL"),
    REVOIL("REVOIL"),
    SHELL("SHELL");

    private final String name;

    private StationBrand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
