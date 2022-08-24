import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Event extends Task {
    /**
     * A public constructor to initialise an Event task.
     * @param description The details of the task.
     * @param time The time which the task must be done by.
     */
    public Event(String description, String time) {
        super(description);
        //Ensure that the time input is proper.
        String[] arr = time.split("-");
        if (arr.length != 3) {
            throw new IncompleteCommandException(description, "Event", "Enter an appropriate date and time format");
        }
        for (String s : arr) {
            Integer.parseInt(s);
        }
        this.time = LocalDate.parse(time);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " +
                this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}