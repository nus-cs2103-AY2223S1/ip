import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime byTime;

    public Deadline (TaskType type, String name, String timeStr) {
        super(type, name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm, d/MM/yyyy");
        LocalDateTime time = LocalDateTime.parse(timeStr, formatter);
        this.byTime = time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a, EEE, d MMM yyyy");
        return "[D]" + super.toString()
                + "(by: " + byTime.format(formatter) + ")";
    }
}

