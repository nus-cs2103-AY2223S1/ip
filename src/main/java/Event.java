import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    String date;
    boolean isDone;

    public Event(String description, boolean isDone, String date) {
        super(description.trim());
        this.date = date.trim();
        Task.taskCount++;
    }

    @Override
    public String toString() {
//        LocalDate d = LocalDate.parse(this.date);
//        String formatDate = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("E | %s | %s | %s", this.getStatusIcon(), this.description, this.date);
    }

}
