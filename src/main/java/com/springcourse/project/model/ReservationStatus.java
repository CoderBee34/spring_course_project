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
        stringToStatusMap.put("Active", ACTIVE);
        stringToStatusMap.put("Pending", PENDING);
        stringToStatusMap.put("Confirmed", CONFIRMED);
        stringToStatusMap.put("Completed", COMPLETED);
        stringToStatusMap.put("Cancelled", CANCELLED);
        stringToStatusMap.put("None", NONE);
    }
    public static ReservationStatus fromString(String status) {
        return stringToStatusMap.get(status);
    }
}
