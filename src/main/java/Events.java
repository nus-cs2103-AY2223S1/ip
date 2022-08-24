import java.time.LocalDate;
import java.time.Month;

public class Events extends Task {
    LocalDate atDate;
    Events(String name, LocalDate atDate) {
        super(name);
        this.atDate = atDate;
    }
    @Override
    public String toString() {
        int year = atDate.getYear();
        int day = atDate.getDayOfMonth();
        Month month = atDate.getMonth();
        return "[E]" + super.toString() + "(by: " + day + " " + month + " " + year + ")";
    }
}
