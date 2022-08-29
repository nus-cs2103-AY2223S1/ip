package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime dateTime;

    public Deadline(LocalDateTime dateTime, String description) {
        super(description, "D");
        this.dateTime = dateTime;
    }

    public Deadline(LocalDateTime dateTime, String description, boolean isDone) {
        super(description, "D", isDone);
        this.dateTime = dateTime;
    }

    /**
     * Returns a String format of date and time of the deadline
     * @return string formatted date and time
     */
    public String getDateTime() {
        return " (by: " +
                this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy', ' hh:mm a")) + ")";
    }

    /**
     * Returns a LocalDateTime object that the deadline needs to be completed by
     * @return local date and time object
     */
    public LocalDateTime getRawDateTime() {
        return this.dateTime;
    }

    /**
     * Returns a String format of this deadline, to be written to TXT file
     * For example:
     * D |   | CS2103 | (by: Aug 29 2022, 09:30 PM)
     * @return string formatted deadline
     */
    @Override
    public String printText() {
        return super.printText() + " | " + this.getDateTime();
    }

    @Override
    public String toString() {
        return "[" + this.getCode() + "]" + super.toString() + this.getDateTime();
    }
}