package com.springcourse.project.model;

import java.util.HashMap;
import java.util.Map;

public enum CarType {
    ECONOMY,
    PEOPLE_CARRIER,
    ESTATE,
    SUV,
    STANDARD,
    CONVERTIBLE,
    LUXURY;
    private static final Map<String, CarType> stringToCarTypeMap = new HashMap<>();

    static {
        stringToCarTypeMap.put("Economy", ECONOMY);
        stringToCarTypeMap.put("People Carrier", PEOPLE_CARRIER);
        stringToCarTypeMap.put("Estate", ESTATE);
        stringToCarTypeMap.put("SUV", SUV);
        stringToCarTypeMap.put("Standard", STANDARD);
        stringToCarTypeMap.put("Convertible", CONVERTIBLE);
        stringToCarTypeMap.put("Luxury", LUXURY);
    }
    public static CarType fromString(String type) {
        return stringToCarTypeMap.get(type);
    }

}
