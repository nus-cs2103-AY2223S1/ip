import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;
    protected LocalTime time;

    public Deadline(String description, LocalDate by, LocalTime time, TaskType type) {
        super(description, type);
        this.by = by;
        this.time = time;
    }

    @Override
    public boolean isDateEqual(LocalDate date) {
        return by.isEqual(date);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " | " + by + " " + time;
    }

    @Override
    public String toString() {
        String str = "";
        if (time != null) {
            str = time.format(DateTimeFormatter.ofPattern("HH:mm "));
        }
        str += by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + "(by: " + str + ")";
    }
}
