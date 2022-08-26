import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    String date;

    public Deadline(String description, String date) {
        super(description.trim());
        this.date = date;
        Task.taskCount++;
    }

    @Override
    public String toString() {
        if (this.date.charAt(0) == ' ') this.date = this.date.substring(1);
        LocalDate d = LocalDate.parse(this.date);
        String formatDate = d.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return String.format("[D] [%s] %s (by: %s)", this.getStatusIcon(), this.description, formatDate);
    }


}
