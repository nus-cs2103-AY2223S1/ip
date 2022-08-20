import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    LocalDate date;
    
    public Deadline(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
    }
    
    @Override
    public String writeString() {
        return String.format("D | %d | %s | %s", super.isDone ? 1 : 0, super.description, date);
    }
    
    @Override
    public String toString() {
        return String.format("[D]%s by: %s", super.toString(), this.date.toString());
    }
}
