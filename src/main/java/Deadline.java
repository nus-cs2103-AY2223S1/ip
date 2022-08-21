import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected String deadline;
    protected LocalDate deadlineDate;
    protected LocalTime deadlineTime;

    public Deadline(String description, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(description);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String TaskInfo() {
        if (deadlineTime == null) {
            return "[D] [" + getStatusIcon() + "] " + description + "(by:" +
                    deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +")";
        } else {
            return "[D] [" + getStatusIcon() + "] " + description + "(by:" +
                    deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " " + deadlineTime +")";
        }

    }
}
