import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public boolean byThisDate(LocalDate date){
        return this.date.isBefore(date);
    }

    public String toStringDate() {
        return super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
