package duke.models;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class RecurringEventParser {
    private final Pattern pattern = Pattern.compile("\\S+\\s/at\\s\\S+\\s/end\\s\\S+");
    private final String RECURRINGEVENT_FORMAT_ERROR =
            "Recurring event must be in this format: <Description> /at <DateTime> /end <DateTime>";
    private final String DATE_FORMAT_ERROR =
            "Your date must be a valid date in dd/MM/yyyy format";

    /**
     * Parses the user input string as an event object.
     *
     * @param content The user input containing the event representation to be parsed.
     * @return The event object represented by the user input string.
     * @throws DukeException If the user input does not follow the required patterns.
     */
    public RecurringEvent parseRecurringEvent(String content) throws DukeException {
        if (!pattern.matcher(content).find()) {
            throw new DukeException(RECURRINGEVENT_FORMAT_ERROR);
        }
        String[] detail = content.split("\\s/at\\s", 2);
        try {
            String[] dates = detail[1].split("\\s/end\\s", 2);
            LocalDate startDate = LocalDate.parse(dates[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate endDate = LocalDate.parse(dates[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return new RecurringEvent(detail[0], startDate, endDate);
        } catch (DateTimeParseException e) {
            throw new DukeException(DATE_FORMAT_ERROR);
        }
    }
}
