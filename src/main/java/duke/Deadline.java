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
