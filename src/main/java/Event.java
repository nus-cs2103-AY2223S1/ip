import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    protected String at;
    protected LocalDate at;

    public Event(String name, String at) {
    public Event(String name, LocalDate at) {
        super(name);
        this.at = at;
    }

<<<<<<< HEAD
    public String getAt() {
        return this.at;
=======
    public void changeDateFormat() {
        this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
>>>>>>> branch-Level-8
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                "(at:" + at + ")";
                "(at: " + at + ")";
    }
}
