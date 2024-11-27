package com.springcourse.project.model;

import java.util.HashMap;
import java.util.Map;

public enum CarStatus {
    AVAILABLE,
    RESERVED,
    LOANED,
    LOST,
    BEING_SERVICED;
    private static final Map<String, CarStatus> stringToStatusMap = new HashMap<>();

    static {
        stringToStatusMap.put("Available", AVAILABLE);
        stringToStatusMap.put("Reserved", RESERVED);
        stringToStatusMap.put("Loaned", LOANED);
        stringToStatusMap.put("Lost", LOST);
        stringToStatusMap.put("Being Serviced", BEING_SERVICED);
    }

    public static CarStatus fromString(String status) {
        return stringToStatusMap.get(status);
    }
}
