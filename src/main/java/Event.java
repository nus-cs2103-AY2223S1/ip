import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }
    @Override
    public String getTaskType() {
        return "E";
    }
    @Override
    public String toString() {
        String formattedDate = at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[E]" + super.toString() + " (at: " + formattedDate + ")";
    }

    @Override
    String saveStringToFile() {
        return String.format("%s%s\n", super.saveStringToFile(), at);
    }
}
