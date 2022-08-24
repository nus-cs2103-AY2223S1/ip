import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    protected  LocalDate date;

    public Event (String description, LocalDate date){
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String saveFormat() {
        if (this.isDone) {
            return "E | 1 | " + this.description + " | " + this.date;
        } else {
            return "E | 0 | " + this.description + " | " + this.date;
        }
    }
} 
