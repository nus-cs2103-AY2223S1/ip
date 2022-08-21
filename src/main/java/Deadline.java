import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {
    protected LocalDate time;
    public Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }
    @Override
    public String toString() {
        String s = super.toString();
        return "[D]" + s + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +")";
    }
}