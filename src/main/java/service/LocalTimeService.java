package service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.TimeZone;

@Service("localTime")
public class LocalTimeService {
    private final static String TIME_FORMAT = "HH:mm:ss.SSS";

    public String getLocalTime(String aTimeZone) {
        boolean isValid = Arrays.asList(TimeZone.getAvailableIDs()).contains(aTimeZone);
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());

        if (isValid) {
            now = LocalDateTime.now(ZoneId.of(aTimeZone));
        }

        return formatTime(now);
    }

    private String formatTime(LocalDateTime aTimeToFormat) {
        return aTimeToFormat.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }
}
