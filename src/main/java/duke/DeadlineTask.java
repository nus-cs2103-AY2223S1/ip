package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The  DeadlineTask class is a Task that takes a deadline.
 */
public class DeadlineTask extends Task {

    private static final String COLON = ":";

    private LocalDate date;
    private LocalTime time;

    /**
     * Constructor for a DeadlineTask object.
     *
     * @param description    description of the task.
     * @param by             Date of the deadline.
     */
    public DeadlineTask(String description, String by) throws DukeException {
        super(description);
        String[] dateParts = by.split(" ");
        if (dateParts.length != 2) {
            throw new DukeException("Deadline Date and Time is specified wrongly");
        }
        this.date = LocalDate.parse(dateParts[0]);
        String temp = "";
        for (int i = 0; i < dateParts[1].length(); i++) {
            temp += dateParts[1].charAt(i);
            if (i == 1) {
                temp += COLON;
            }
        }
        this.time = LocalTime.parse(temp);
    }

    /**
     * formats date and time to a string that would be added into the text document.
     *
     * @return String added into the text document.
     */
    public String dateTimeString() {
        return this.date.toString() + " " + this.time.toString().replace(":", "");
    }

    @Override
    public String toString() {
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("h:mm a");
        return "[D]" + super.toString() + " (by: "
                + this.date.format(formatterDate) + " "
                + this.time.format(formatterTime) + ")";
    }
}
