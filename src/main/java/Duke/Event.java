package Duke;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private LocalDate date;
    private LocalDateTime dateTime;

    Event(String name, String dateTime) {
        super(name);
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.dateTime = LocalDateTime.parse(dateTime, format);
        } catch (DateTimeParseException e) {
            System.out.println("Incorrect date format");
            this.dateTime = null;
        }
    }

    Event(String name, String dateTime, boolean exist) {
        super(name);
        this.dateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }


    @Override
    boolean isToDo() {
        return false;
    }

    @Override
    LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        String out = "[E]";
        out += super.toString();
        out += " (at " + this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
        return out;
    }
}
