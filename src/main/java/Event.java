import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate date;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public boolean onThisDate(LocalDate date){
        return this.date.equals(date);
    }

    public String toStringDate() {
        return super.toString();
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
