package com.springcourse.project.model;

import java.util.HashMap;
import java.util.Map;

public enum CarStatus {
    AVAILABLE,
    RESERVED,
    LOANED,
    BEING_SERVICED,
    LOST;
    private static final Map<String, CarStatus> stringToStatusMap = new HashMap<>();

    static {
        stringToStatusMap.put("available", AVAILABLE);
        stringToStatusMap.put("reserved", RESERVED);
        stringToStatusMap.put("loaned", LOANED);
        stringToStatusMap.put("being serviced", BEING_SERVICED);
        stringToStatusMap.put("lost", LOST);
    }

    public static CarStatus fromString(String status) {
        return stringToStatusMap.get(status);
    }
}
