package duke.task;

import duke.DukeException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Event extends Task {
    protected LocalDateTime dateTime;
    protected static DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static DateTimeFormatter dateTimeOutputFormatter = DateTimeFormatter.ofPattern("dd LLL yyyy hh:mma");
    protected static DateTimeFormatter dateInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructs an event with the specified description, date and time.
     *
     * @param description The specified description.
     * @param at The specified date and time.
     * @throws DukeException If the date is not provided in the right format.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.dateTime = LocalDateTime.parse(at, dateTimeInputFormatter);
        } catch (Exception e) {
            throw new DukeException("Input your date and time in the format yyyy-MM-dd HHmm!");
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
        return new Event(line.substring(10, line.indexOf("(at:") - 1),
                LocalDateTime.parse(line.substring(line.indexOf("(at:") + 5, line.lastIndexOf(")")),
                        dateTimeOutputFormatter));
    }

    /**
     * {@inheritDoc}
     */
    public boolean isOnThisDate(String dateStr) throws DukeException {
        if (dateStr.isBlank()) {
            throw new DukeException("Date cannot be blank!");
        }
        try {
            LocalDate date = LocalDate.parse(dateStr, dateInputFormatter);
            return date.equals(this.dateTime.toLocalDate());
        } catch (Exception e){
            throw new DukeException("Please type in the date in the format yyyy-MM-dd");
        }
    }

    public boolean doesDescriptionContain(String input) throws DukeException {
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