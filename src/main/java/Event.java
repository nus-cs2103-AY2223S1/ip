import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    private LocalDate date;
    private LocalTime time;
    public Event(String description, String timing) {
        super(description);
        String[] timingParams = timing.split(" ");
        if (timingParams.length == 2) {
            this.time = LocalTime.parse(timingParams[1], DateTimeFormatter.ofPattern("HHmm"));
        }
        this.date = LocalDate.parse(timingParams[0], DateTimeFormatter.ofPattern("d/M/yyyy"));
    }
    @Override
    public String getType() {
        return "E";
    }
    @Override
    public String getDescription() {
        return String.format("%s (at: %s%s)",
                super.getDescription(),
                this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                (this.time != null ? this.time.format(DateTimeFormatter.ofPattern(" hh:mm a")) : "")
        );
    }

    @Override
    public boolean isEqualDate(LocalDate date) {
        return this.date.equals(date);
    }
}
