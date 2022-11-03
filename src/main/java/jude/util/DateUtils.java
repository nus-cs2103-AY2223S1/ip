package jude.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * DateUtils is a utilities class which helps to deal with dates.
 */
public class DateUtils {

    /**
     * Returns the epoch time (time since 1 January 1970, 0:00 UTC) assuming the time zone is the
     * same as the system default time.
     *
     * @param time The time to be converted to epoch time.
     * @return The epoch time corresponding to the input time.
     */
    private static long getEpochTime(LocalDateTime time) {
        //@@author cheeheng-reused
        // Reused from
        // https://stackoverflow.com/questions/22990067/how-to-extract-epoch-from-localdate-and-localdatetime
        // with minor modifications.
        ZoneId zoneId = ZoneId.systemDefault();
        return time.atZone(zoneId).toEpochSecond();
        //@@author
    }

    /**
     * Calculates the time from {@code from} to {@code to} in seconds.
     * If {@code from} is after {@code to}, then the result is negative.
     *
     * @param from Starting time.
     * @param to Ending time.
     * @return The time from {@code from} to {@code to} in seconds.
     */
    public static long calculateTimeDifference(LocalDateTime from, LocalDateTime to) {
        return getEpochTime(to) - getEpochTime(from);
    }
}
