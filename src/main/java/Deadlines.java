import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadlines extends Task {
    private LocalDate date;
    public Deadlines(String description, String date){
        super(description);
        this.date = LocalDate.parse(date);
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + "(" + this.date + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
