import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Deadline extends Task {
    /**
     * A public constructor to initialise a Deadline task.
     * @param description The details of the task.
     * @param time The time which the task must be done by.
     */
    public Deadline(String description, String time) {
        super(description);
        //Ensure that the time input is proper.
        String[] arr = time.split("-");
        if (arr.length != 3) {
            throw new IncompleteCommandException(description, "Deadline", "Enter an appropriate date and time format");
        }
        for (String s : arr) {
            Integer.parseInt(s);
        }
        this.time = LocalDate.parse(time);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " +
                this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}