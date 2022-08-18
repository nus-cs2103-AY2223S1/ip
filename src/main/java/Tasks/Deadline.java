package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate date;

    public Deadline(String msg, LocalDate date) {
        super(msg);
        this.date = date;
    }

    @Override
    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("dd MMM uuuu"));
    }

    @Override
    public String toString() {
        return String.format("%s%s (by: %s)", "[D]", super.toString(), this.date.toString());
    }
}
