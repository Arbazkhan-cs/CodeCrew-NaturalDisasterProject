package com.easc01.disastermediaapi.util;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    public static String instantToStringV1(Instant instant) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .withZone(ZoneOffset.UTC);
        // Truncate to seconds to remove nanoseconds
        Instant truncatedInstant = instant.truncatedTo(java.time.temporal.ChronoUnit.SECONDS);
        return formatter.format(truncatedInstant);
    }
}
