import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String input, String deadline) {
        super(input, "");
        this.deadline = getTime(deadline);
    }

    public Deadline(String input, boolean done, String deadline) {
        super(input, done, "");
        this.deadline = getTime(deadline);
    }

    public Deadline(String input, boolean done, LocalDateTime deadline) {
        super(input, done, "");
        this.deadline = deadline;
    }

    public Deadline markDone() {
        return new Deadline(this.getVal(), true, this.deadline);
    }

    public Deadline markUndone() {
        return new Deadline(this.getVal(), false, this.deadline);
    }

    @Override
    public String getTiming() {
        int day = this.deadline.getDayOfMonth();
        Month month = this.deadline.getMonth();
        int year = this.deadline.getYear();
        int hour = this.deadline.getHour();
        int min = this.deadline.getMinute();
        return(String.format("%s %s %s %02d:%02d", day, month, year, hour, min));
    }
    @Override
    public String toString() {
        if(this.getDone()) {
            return (String.format("[D][X] %s (%s)", this.getVal(), this.getTiming()));
        }
        else {
            return (String.format("[D][ ] %s (%s)", this.getVal(), this.getTiming()));
        }
    }

    private LocalDateTime getTime(String str) {
        //from stackoverflow
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return dateTime;
    }
}
