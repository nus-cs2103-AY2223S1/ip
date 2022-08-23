import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task{
    private LocalDate day;
    private LocalTime time;

    public Deadline(String description, LocalDate day) {
        super(description);
        this.day = day;
    }

    public Deadline(String description, LocalDate day, LocalTime time) {
        super(description);
        this.day = day;
        this.time = time;
    }

    @Override
    public boolean happensOn(LocalDate searchDate) {
        return this.day.equals(searchDate);
    }

    @Override
    public String fileDescription() {
        if (time == null) {
            return "D | " + super.fileDescription() + " | "
                    + day;
        } else {
            return "D | " + super.fileDescription() + " | "
                    + day + " | " + time;
        }
    }

    @Override
    public String toString() {
        if (time == null) {
            return "[D]" + super.toString() + " (by: " + day.format(super.dateFormatter) + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + day.format(super.dateFormatter) + ", "
                    + time.format(super.timeFormatter) + ")";
        }
    }
}
