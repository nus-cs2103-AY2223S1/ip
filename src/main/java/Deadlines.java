import java.time.LocalDate;
import java.time.Month;

public class Deadlines extends Task {
    LocalDate byDate;
    Deadlines(String name, LocalDate byDate) {
        super(name);
        this.byDate = byDate;
    }
    @Override
    public String toString() {
        int year = byDate.getYear();
        int day = byDate.getDayOfMonth();
        Month month = byDate.getMonth();
        return "[D]" + super.toString() + "(by: " + day + " " + month + " " + year + ")";
    }
}
