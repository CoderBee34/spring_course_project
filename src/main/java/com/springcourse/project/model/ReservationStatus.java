package com.springcourse.project.model;

import java.util.HashMap;
import java.util.Map;

public enum ReservationStatus {
    ACTIVE,
    PENDING,
    CONFIRMED,
    COMPLETED,
    CANCELLED,
    NONE;

    private static final Map<String, ReservationStatus> stringToStatusMap = new HashMap<>();

    static {
        stringToStatusMap.put("active", ACTIVE);
        stringToStatusMap.put("pending", PENDING);
        stringToStatusMap.put("confirmed", CONFIRMED);
        stringToStatusMap.put("completed", COMPLETED);
        stringToStatusMap.put("cancelled", CANCELLED);
        stringToStatusMap.put("none", NONE);
    }
    public static ReservationStatus fromString(String status) {
        return stringToStatusMap.get(status);
    }
}
