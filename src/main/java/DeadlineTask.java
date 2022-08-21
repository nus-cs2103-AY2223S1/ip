import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The  DeadlineTask class is a Task that takes a deadline.
 */
public class DeadlineTask extends Task {

    protected static final String COLON = ":";

    protected LocalDate date;
    protected LocalTime time;
    /**
     * Constructor for a DeadlineTask object.
     * @param description    Name of the task.
     * @param by             Date of the deadline.
     */
    public DeadlineTask(String description, String by) {
        super(description);
        String[] dateParts = by.split(" ");
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

    @Override
    public String toString() {
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter formatterTime =DateTimeFormatter.ofPattern("h:mma");
        return "[D]" + super.toString() + " (by: "
                + this.date.format(formatterDate) + " "
                + this.time.format(formatterTime) + ")";
    }
}
