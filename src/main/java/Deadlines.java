import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task{
    String by;
    LocalDate byDate = null;
    String type;
    Deadlines(String name, String by){
        super(name);
        this.by = by;
        this.type = "[D]";
    }

     Deadlines(String name, LocalDate byDate) {
        super(name);
        this.byDate = byDate;
        this.type = "[D]";
    }
    @Override
    public String toString() {
        String s = this.type + super.getStatus() + " (by: ";
        if (this.byDate != null) {
            return s + this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return s + this.by + ")";
        }
    }
}
