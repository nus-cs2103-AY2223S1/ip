import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by;
    protected LocalTime by1;

    public Deadline(String description, LocalDate by, LocalTime by1, TaskType type) {
        super(description, type);
        this.by = by;
        this.by1 = by1;
    }

    @Override
    public boolean isDateEqual(LocalDate date) {
        return by.isEqual(date);
    }

    @Override
    public String toString() {
        String str = "";
        if (by1 != null) {
            str = by1.format(DateTimeFormatter.ofPattern("HH:mm "));
        }
        str += by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + "(by: " + str + ")";
    }
}
