import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate at;
    public Event(String input) throws DateTimeParseException {
        String[] strArr = input.split("/at");
        this.description = strArr[0].trim();
        try {
            this.at = LocalDate.parse(strArr[1].trim());
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date format");
            throw e;
        }
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.Event;
    }

    @Override
    public String toString() {
        String date  = this.at.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        String head = "[E][" + this.getStatusIcon() + "] ";
        String body = this.description + " (at: " + date + ")";
        return head + body;
    }
}
