package duke.task;

import duke.task.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime date;

    public Event(String description, String dateStr) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.date = LocalDateTime.parse(dateStr, formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDateStr() + ")";
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public String getDateStr() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE, hh:mma dd MMMM yyyy");
        return this.date.format(formatter);
    }

    @Override
    public String save() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dateStr = this.date.format(formatter);
        return "E" + super.save() + " | " + dateStr;
    }

    public boolean isOnDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        LocalDate other = this.date.toLocalDate();
        return date.equals(other);
    }
}
