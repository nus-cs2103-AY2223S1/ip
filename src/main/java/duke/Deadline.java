package duke;

import duke.exceptions.ImproperDeadlineFormatException;
import duke.exceptions.ImproperFormatException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalTime time;
    private LocalDate date;

    private String by;

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("h:mm a");

    /*
     * Create Deadline with description, date in MMM DD YYYY, time in hh:mm aa
     * @throws ImproperFormatException when by does not follow specified format
     */
    public Deadline(String description, String by) throws ImproperDeadlineFormatException {
        super(description);
        this.by = by; // by: " YYYY-MM-DD hh:mm"
        try {
            String[] arr = by.split(" "); // arr: ["", "YYYY-MM-DD", "hh:mm"]
            this.date = LocalDate.parse(arr[1]);
            this.time = LocalTime.parse(arr[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ImproperDeadlineFormatException();
        } catch (DateTimeParseException e) {
            throw new ImproperDeadlineFormatException();
        }
    }

    /*
     * @return String representation in
     *         "[D] [status] task (by: MMM DD YYYY h:mm a)"
     */
    @Override
    public String toString() {
        return "[D] "
                + this.getStatusIcon()
                + " " + super.description
                + " (by: "
                + date.format(DATE_FORMAT)
                + ", "
                + time.format(DATE_FORMAT)
                + ")";
    }

    /*
     * return String representation of deadline to be stored in Storage
     * @return String representation in
     *         "D|0 or 1|task|by|"
     *         where 1 represents the task is marked and 0 otherwise
     */
    @Override
    public String toSaveVersion() {
        if (this.isDone()) {
            return "D|1|"
                    + super.description
                    + "|"
                    + this.by
                    + "\n";
        }
        return "D|0|"
                + super.description
                + "|"
                + this.by
                + "\n";
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline x = (Deadline) obj;
            if (this.description == null
                    || this.by == null
                    || x.description == null
                    || x.by == null) {
                return false;
            }
            return this.description.equals(x.description)
                    && this.by.equals(x.by);
        }

        return false;
    }
}
