import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    String date;

    public Event(String description, String date) {
        super(description.trim());
        this.date = date;
        Task.taskCount++;
    }

    @Override
    public String toString() {
        if (this.date.charAt(0) == ' ') this.date = this.date.substring(1);
        LocalDate d = LocalDate.parse(this.date);
        String formatDate = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[E] [%s] %s (at: %s)", this.getStatusIcon(), this.description, formatDate);
    }

}
