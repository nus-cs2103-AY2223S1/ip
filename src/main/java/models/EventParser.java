package models;

import exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class EventParser extends Parser {
    private final Pattern pattern = Pattern.compile("[\\S+]\\s/at\\s[\\S+]");
    private final String EVENT_FORMAT_ERROR =
            "Deadline must be in this format: <Description> /by <DateTime>\n";
    private final String DATE_FORMAT_ERROR =
            "Your date must be a valid date in dd/MM/yyyy format\n";

    public Event parseEvnet(String content) throws DukeException {
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

