package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(by, formatter);
        this.by = localDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String date = this.by.format(formatter);
        return "[D]" + super.toString() + String.format(" (by: %s)", date);
    }

    @Override
    public LocalDate getDate() {
        return this.by;
    }

    @Override
    public String getTaskType() {
        return "D";
    }
}
