package duke.date;

import java.time.LocalDateTime;
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
                put("today|tdy", () -> LocalDateTime.now().toLocalDate().atTime(23, 59));
                put("morning", () -> LocalDateTime.now().toLocalDate().atTime(8, 0));
                put("evening", () -> LocalDateTime.now().toLocalDate().atTime(18, 0));
                put("night", () -> LocalDateTime.now().toLocalDate().atTime(20, 0));
                put("tomorrow|tmr", () -> LocalDateTime.now().plusDays(1));
                put("yesterday|yst", () -> LocalDateTime.now().minusDays(1));
                put("next week|next wk", () -> LocalDateTime.now().plusDays(7));
                put("last week|last wk", () -> LocalDateTime.now().minusDays(7));
                put("next month|next mth", () -> LocalDateTime.now().plusMonths(1));
                put("last month|last mth", () -> LocalDateTime.now().minusMonths(1));
                put("next year|next yr", () -> LocalDateTime.now().plusYears(1));
                put("last year|last yr", () -> LocalDateTime.now().minusYears(1));
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
