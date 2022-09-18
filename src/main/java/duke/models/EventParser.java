package duke.models;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 * Represents a parser for the event object.
 *
 * @author Zhu Yuanxi
 */
public class EventParser extends Parser {
    private final Pattern pattern = Pattern.compile("[\\S+]\\s/at\\s[\\S+]");
    private final String EVENT_FORMAT_ERROR =
            "Event must be in this format: <Description> /at <DateTime>";
    private final String DATE_FORMAT_ERROR =
            "Your date must be a valid date in dd/MM/yyyy format";

    /**
     * Parses the user input string as an event object.
     *
     * @param content The user input containing the event representation to be parsed.
     * @return The event object represented by the user input string.
     * @throws DukeException If the user input does not follow the required patterns.
     */
    public Event parseEvent(String content) throws DukeException {
        if (!pattern.matcher(content).find()) {
            throw new DukeException(EVENT_FORMAT_ERROR);
        }
        String[] detail = content.split("\\s/at\\s", 2);
        try {
            LocalDate date = LocalDate.parse(detail[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return new Event(detail[0], date);
        } catch (DateTimeParseException e) {
            throw new DukeException(DATE_FORMAT_ERROR);
        }
    }
}

