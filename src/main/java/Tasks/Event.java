package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(at, formatter);
        this.at = localDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String date = this.at.format(formatter);
        return "[E]" + super.toString() + String.format(" (at: %s)", date);
    }

    @Override
    public LocalDate getDate() {
        return this.at;
    }

    @Override
    public String getTaskType() {
        return "E";
    }
}
