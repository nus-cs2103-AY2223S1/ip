package duke;

import duke.exceptions.ImproperFormatException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalTime time;
    private LocalDate date;

    private String by;

    /*
     * Create Deadline with description, date in MMM DD YYYY, time in hh:mm aa
     * @throws ImproperFormatException when by does not follow specified format
     */
    public Deadline(String description, String by) throws ImproperFormatException {
        super(description);
        this.by = by;
        try {
            String[] arr = by.split(" ");
            this.date = LocalDate.parse(arr[1]);
            this.time = LocalTime.parse(arr[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ImproperFormatException();
        } catch (DateTimeParseException e) {
            throw new ImproperFormatException();
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
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ", "
                + time.format(DateTimeFormatter.ofPattern("h:mm a"))
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
        } else {
            return "D|0|"
                    + super.description
                    + "|"
                    + this.by
                    + "\n";
        }
    }
}
