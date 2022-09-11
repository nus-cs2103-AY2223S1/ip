package gina.task;

import gina.GinaException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Represents an event.
 */
public class Event extends Task {
    private static DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static DateTimeFormatter dateTimeOutputFormatter = DateTimeFormatter.ofPattern("dd LLL yyyy hh:mma");
    private static DateTimeFormatter dateInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected LocalDateTime dateTime;
    private static final int DESCRIPTION_START_INDEX = 10;

    /**
     * Constructs an event with the specified description, date and time.
     *
     * @param description The specified description.
     * @param at The specified date and time.
     * @throws GinaException If the date is not provided in the right format.
     */
    public Event(String description, String at) throws GinaException {
        super(description);
        try {
            this.dateTime = LocalDateTime.parse(at, dateTimeInputFormatter);
        } catch (Exception e) {
            throw new GinaException("Input your date and time in the format yyyy-MM-dd HHmm!");
        }
    }

    private Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Creates an event from the data from the storage.
     *
     * @param line The line representing the event in storage.
     * @return The event with the specified details.
     */
    public static Event createEventFromString(String line) {
        return new Event(line.substring(DESCRIPTION_START_INDEX, line.indexOf("(at:") - 1),
                LocalDateTime.parse(line.substring(line.indexOf("(at:") + 5, line.lastIndexOf(")")),
                        dateTimeOutputFormatter));
    }

    /**
     * {@inheritDoc}
     */
    public boolean isOnThisDate(String dateStr) throws GinaException {
        if (dateStr.isBlank()) {
            throw new GinaException("Aren't you gonna tell me when the event is?");
        }
        try {
            LocalDate date = LocalDate.parse(dateStr, dateInputFormatter);
            return date.equals(this.dateTime.toLocalDate());
        } catch (Exception e) {
            throw new GinaException("Tell me the date in the format yyyy-MM-dd.\n"
                    + "Gina Linetti's time is precious.");
        }
    }

    public boolean doesDescriptionContain(String input) {
        return Arrays.asList(description.split(" ")).contains(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime.format(dateTimeOutputFormatter) + ")";
    }
}
