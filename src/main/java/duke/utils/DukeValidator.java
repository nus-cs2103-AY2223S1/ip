package duke.utils;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DukeValidator {
    /* Regex pattern matcher for a date in yyyy-mm-dd format */
    private static final Pattern MATCH_DATE = Pattern.compile("^(?<year>\\d{4})-(?<month>0[0-9]|1[0-2])-(?<day>0[0-9]|1[0-9]|2[0-9]|3[0-1])$");
    private static final String ERROR_INVALID_DATE = "Date is not in the specified yyyy-mm-dd format!";

    public static LocalDate parseDate(String date) throws DukeException {
        Matcher matcher = MATCH_DATE.matcher(date);
        if (!matcher.find()) {
            throw new DukeException(DukeValidator.ERROR_INVALID_DATE);
        }
        return LocalDate.parse(date);
    }
}
