import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_RESET = "\u001B[0m";

    protected String date;
    DateTimeFormatter dTF = DateTimeFormatter.ofPattern("MMM d yyyy");
    public Event(String description, String date, String taskType) {
        super(description, taskType);
        this.date = date;
    }
    public String getDate() {
        return this.date;
    }
    @Override
    public String toString() {
        LocalDate d = LocalDate.parse(this.date);
        String formattedDate = d.format(dTF);
        return super.toString() + String.format(ANSI_RED + " (on: %s)", formattedDate + ANSI_RESET);
    }
}
