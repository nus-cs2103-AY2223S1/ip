package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent a deadline task that needs to be done by a certain date
 */
public class Deadline extends Task {
    private LocalDate datetime;

    /**
     * Deadline constructor with the specified description and datetime
     *
     * @param description a {@link String} indicating the deadline description
     * @param datetime a {@link LocalDate} indicating the deadline date
     */
    public Deadline(String description, LocalDate datetime) {
        super(description);
        this.datetime = datetime;
    }

    /**
     * Deadline constructor with the specified isDone, description, and datetime
     *
     * @param isDone a {@link String} indicating if the deadline has been marked as done
     * @param description a {@link String} indicating the deadline description
     * @param datetime a {@link LocalDate} indicating the deadline date
     */
    public Deadline(String isDone, String description, LocalDate datetime) {
        super(isDone, description);
        this.datetime = datetime;
    }

    /**
     * Returns a {@link String} representation of a deadline
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a {@link String} representation of a deadline
     *
     * @return String
     */
    @Override
    public String toTxt() {
        return String.format("D @@ %s @@ %s", super.toTxt(), datetime);
    }
}
