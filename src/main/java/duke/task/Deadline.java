package duke.task;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String desc, LocalDateTime date) {
        super(desc);
        deadline = date;
    }

    private String getDeadlineString() {
        int day = this.deadline.getDayOfMonth();
        int month = this.deadline.getMonthValue();
        int year = this.deadline.getYear();
        int time = this.deadline.getHour() * 100 + this.deadline.getMinute();
        return month < 10
                ? String.format("%d/0%d/%d %d", day, month, year, time)
                : String.format("%d/%d/%d %d", day, month, year, time);
    }

    @Override
    public String tofileString() {
        return "D|" + super.tofileString() + "|" + this.getDeadlineString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDeadlineString() + ")";
    }

}
