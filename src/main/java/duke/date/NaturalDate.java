package duke.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Set;
import java.util.function.Supplier;

import duke.exception.DukeException;

/**
 * Handles some limited natural date parsing supported by the program.
 */
public class NaturalDate {
    // hashmap of the supported natural dates, where the key is the regex of the supported
    // natural date, and the key is a function returning the local datetime of the associated
    // natural date
    private static final HashMap<String, Supplier<LocalDateTime>> SUPPORTED_NATURAL_DATES =
            initialiseNaturalDates();
    private static final Set<String> SUPPORTED_NATURAL_DATES_REGEXP = SUPPORTED_NATURAL_DATES.keySet();

    private static HashMap<String, Supplier<LocalDateTime>> initialiseNaturalDates() {
        return new HashMap<>() {
            {
                put("now", LocalDateTime::now);
                put("today|tdy", () -> LocalDate.now().atTime(23, 59));
                put("morning", () -> LocalDate.now().atTime(8, 0));
                put("evening", () -> LocalDate.now().atTime(18, 0));
                put("night", () -> LocalDate.now().atTime(20, 0));
                put("tomorrow|tmr", () -> LocalDateTime.now().plusDays(1));
                put("yesterday|yst", () -> LocalDateTime.now().minusDays(1));
                put("next week|next wk", () -> LocalDateTime.now().plusDays(7));
                put("last week|last wk", () -> LocalDateTime.now().minusDays(7));
                put("next month|next mth", () -> LocalDateTime.now().plusMonths(1));
                put("last month|last mth", () -> LocalDateTime.now().minusMonths(1));
                put("next year|next yr", () -> LocalDateTime.now().plusYears(1));
                put("last year|last yr", () -> LocalDateTime.now().minusYears(1));
                put("mon|monday", () -> LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
                put("tues|tuesday", () -> LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.TUESDAY)));
                put("wed|weds|wednesday", () -> LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY)));
                put("thur|thurs|thursday", () -> LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.THURSDAY)));
                put("fri|friday", () -> LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY)));
                put("sat|saturday", () -> LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY)));
                put("sun|sunday", () -> LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY)));
            }
        };
    }

    /**
     * Checks whether a given date is a supported natural date.
     *
     * @param date The date to be checked.
     * @return The regexp of the natural date if the given date is a natural date,
     *         else returns null.
     */
    public static String getNaturalDateRegExp(String date) {
        for (String naturalDateRegexp : SUPPORTED_NATURAL_DATES_REGEXP) {
            if (date.matches(naturalDateRegexp)) {
                return naturalDateRegexp;
            }
        }
        return null;
    }

    /**
     * Parses a natural date expression into a LocalDateTime object.
     *
     * @param naturalDateRegExp The natural date regex associated to the natural date.
     * @return The LocalDateTime object from parsing the natural date expression.
     * @throws DukeException If the provided natural date regex is invalid or not supported.
     */
    public static LocalDateTime getNaturalDate(String naturalDateRegExp) throws DukeException {
        try {
            Supplier<LocalDateTime> getDateTime = SUPPORTED_NATURAL_DATES.get(naturalDateRegExp);
            return getDateTime.get();
        } catch (NullPointerException e) {
            String errorMessage = String.format("Unknown natural date expression %s!", naturalDateRegExp);
            throw new DukeException(errorMessage);
        }
    }
}
