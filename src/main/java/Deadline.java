import java.time.LocalDate;
import java.time.format.*;

public class Deadline extends Task{
    private LocalDate date;

    public Deadline(String task, LocalDate date) {
        super(task);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%s %s (at: %s)", "[D]", super.toString(), this.date.toString());
    }
}
