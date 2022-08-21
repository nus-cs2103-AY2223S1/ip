import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate time;

    public Event(String taskname, LocalDate time) {
        super(taskname);
        this.time = time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy");
        return "[E]" + super.toString() + " (by: " + this.time.format(formatter) + ")";
    }
}
