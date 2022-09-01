package duke.task;
import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime dateTime;
    protected static DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static DateTimeFormatter dateTimeOutputFormatter = DateTimeFormatter.ofPattern("dd LLL yyyy hh:mma");
    protected static DateTimeFormatter dateInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructs a deadline with the specified description, date and time.
     *
     * @param description The specified description.
     * @param by The specified date and time.
     * @throws DukeException If the date is not provided in the right format.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.dateTime = LocalDateTime.parse(by, dateTimeInputFormatter);
        } catch (Exception e) {
            throw new DukeException("Input your date and time in the format yyyy-MM-dd HHmm!");
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
        return new Deadline(line.substring(10, line.indexOf("(by:") - 1),
                LocalDateTime.parse(line.substring(line.indexOf("(by:") + 5, line.lastIndexOf(")")),
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime.format(dateTimeOutputFormatter) + ")";
    }
}