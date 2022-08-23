import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//@@author chengda300
//Reused from https://nus-cs2103-ay2223s1.github.io/website/schedule/week2/project.html
// with minor modifications
public class Event extends Task {
    private String at;
    private LocalDate date;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, LocalDate at) {
        super(description);
        this.date = at;
    }

    @Override
    public String toString() {
        String eventDate = this.at == null
                ? this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : this.at;
        return "[E]" + super.toString() + " (at: " + eventDate + ")";
    }
}
//@@author