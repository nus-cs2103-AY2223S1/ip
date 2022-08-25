import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at.trim();
    }

    public String changeAtFormat(String at) {
        // split the date and the time
        String[] splitDeadline = at.split(" ");
        String givenDate = splitDeadline[0].trim();
        LocalDate outputDate = LocalDate.parse(givenDate);

        String time = splitDeadline[1].trim();
        String date = outputDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        return date + ", " + time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.changeAtFormat(at) + ")";
    }

    @Override
    public String toStorageFormat() {
        int done = isDone ? 1 : 0;
        String res = String.format("D | %d | %s | %s", done, taskName, at);
        return res;
    }
}