import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String description;
    protected boolean isDone;
    protected String deadlineDay;
    protected LocalDate deadlineDateTime;

    public Deadline (String description, String deadlineDay) {
        super(description);
        this.deadlineDay = deadlineDay.replaceAll(" ", "");
    }

    public void stringToDate() {
        try {
            DateTimeFormatter convertFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate deadlineDate = LocalDate.parse(this.deadlineDay, convertFormatter);
            this.deadlineDateTime = deadlineDate;
        } catch (Exception err) {
            DukeException.DateTimeFormatErrorMessage();
            System.out.println(err);
        }
    }

    public String modifedDate() {
        DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String modifiedString = this.deadlineDateTime.format(printFormatter);
        return modifiedString;
    }

    @Override
    public String toString() {
        String s = String.format("[D]%s (by: %s)", super.toString(), this.modifedDate());
        return s;
    }
}
