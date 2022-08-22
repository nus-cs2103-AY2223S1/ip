package duke;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The EventTask is a Task that has at event time.
 */
public class EventTask extends Task {

    protected static final String COLON = ":";

    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructor of a EventTask object
     * @param description      Name of the task
     * @param at               Date of the event.
     */
    public EventTask(String description, String at) throws DukeException {
        super(description);
        String[] dateParts = at.split(" ");
        if (dateParts.length != 2) {
            throw new DukeException("Event Date and Time is specified wrongly");
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

    public String dateTimeString() {
        return this.date.toString() + " " + this.time.toString().replace(":", "");
    }
    @Override
    public String toString() {
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter formatterTime =DateTimeFormatter.ofPattern("h:mm a");
        return "[E]" + super.toString() + " (at: "
                + this.date.format(formatterDate) + " "
                + this.time.format(formatterTime) + ")";
    }
}
