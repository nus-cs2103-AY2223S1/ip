import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.LocalDate;

public class Deadline extends Task {
    private final LocalDate deadline;

    public Deadline(String task, String deadline) {
        super(task, "deadline");
        String[] returnedArray = deadline.split(" ");
        if (returnedArray.length == 1) {
            this.deadline = LocalDate.parse(deadline);
        } else {
            this.deadline = LocalDate.parse(deadline,
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        }
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " +
                deadline.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + ")";
    }
}
