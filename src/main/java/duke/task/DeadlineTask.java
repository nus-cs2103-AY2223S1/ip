package duke.task;

import java.time.LocalDate;

public class DeadlineTask extends Task {
    protected LocalDate by;

    public DeadlineTask(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        int month = by.getDayOfMonth();
        String formattedDate = by.getMonth().toString().substring(0, 3) + " " + (month < 10 ? "0" + month : month) + " " + by.getYear();
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
