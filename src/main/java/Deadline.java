import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private LocalDate by;
    public Deadline(String input) throws DateTimeParseException {
        String[] strArr = input.split("/by");
        this.description = strArr[0].trim();
        try {
            this.by = LocalDate.parse(strArr[1].trim());
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date format");
            throw e;
        }
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.Deadline;
    }

    @Override
    public String toString() {
        String date = this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        String head = "[D][" + this.getStatusIcon() + "] ";
        String body = this.description + " (by: " + date + ")";
        return head + body;
    }
}
