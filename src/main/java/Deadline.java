import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate byDate;
    protected LocalTime byTime;

    public Deadline(String description, String by) {
        super(description);
        String[] dateTimeSplit = by.split(" ");
        this.byDate = LocalDate.parse(dateTimeSplit[0]);
        if (dateTimeSplit.length > 1) {
            this.byTime = LocalTime.parse(dateTimeSplit[1]);
        } else {
            this.byTime = LocalTime.parse("23:59");
        }
    }

    @Override
    public String toString() {
        String formattedDate =  this.byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String formattedTime = this.byTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        return String.format("[D] %s (by: %s %s)", super.toString(), formattedDate, formattedTime);
    }
}