import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    public String getSaveFormat() {
        String isDone = this.isDone ? "1" : "0";
        return "E | " + isDone + " | " + this.description + " | " + this.at;
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
=======
        return "[E]"
                + super.toString()
                + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
>>>>>>> branch-Level-8
    }

}
