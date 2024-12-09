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
        stringToCarTypeMap.put("economy", ECONOMY);
        stringToCarTypeMap.put("people carrier", PEOPLE_CARRIER);
        stringToCarTypeMap.put("estate", ESTATE);
        stringToCarTypeMap.put("suv", SUV);
        stringToCarTypeMap.put("standard", STANDARD);
        stringToCarTypeMap.put("convertible", CONVERTIBLE);
        stringToCarTypeMap.put("luxury", LUXURY);
    }
    public static CarType fromString(String type) {
        return stringToCarTypeMap.get(type);
    }

}
