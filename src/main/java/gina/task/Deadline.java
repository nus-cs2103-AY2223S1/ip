package gina.task;

import gina.GinaException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {
    protected static DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static DateTimeFormatter dateTimeOutputFormatter = DateTimeFormatter.ofPattern("dd LLL yyyy hh:mma");
    protected static DateTimeFormatter dateInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected LocalDateTime dateTime;
    private static final int DESCRIPTION_START_INDEX = 10;

    /**
     * Constructs a deadline with the specified description, date and time.
     *
     * @param description The specified description.
     * @param by The specified date and time.
     * @throws GinaException If the date is not provided in the right format.
     */
    public Deadline(String description, String by) throws GinaException {
        super(description);
        try {
            this.dateTime = LocalDateTime.parse(by, dateTimeInputFormatter);
            assert(dateTime != null);
        } catch (DateTimeParseException e) {
            throw new GinaException("Input your date and time in the format yyyy-MM-dd HHmm!");
        }
    }

    private Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Creates a deadline from the data from the storage.
     *
     * @param line The line representing the deadline in storage.
     * @return The deadline with the specified details.
     */
    public static Deadline createDeadlineFromString(String line) {
        return new Deadline(line.substring(DESCRIPTION_START_INDEX, line.indexOf("(by:") - 1),
                LocalDateTime.parse(line.substring(line.indexOf("(by:") + 5, line.lastIndexOf(")")),
                        dateTimeOutputFormatter));
    }

    /**
     * {@inheritDoc}
     */
    public boolean isOnThisDate(String dateStr) throws GinaException {
        assert(dateStr != null);
        if (dateStr.isBlank()) {
            throw new GinaException("Aren't you going to tell me when the deadline is?");
        }
        try {
            LocalDate date = LocalDate.parse(dateStr, dateInputFormatter);
            return date.equals(this.dateTime.toLocalDate());
        } catch (DateTimeParseException e) {
            throw new GinaException("Tell me the date in the format yyyy-MM-dd.\n" +
                    "Gina Linetti's time is precious.");
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
        return "[D]" + super.toString() + " (by: " + dateTime.format(dateTimeOutputFormatter) + ")";
    }
}
