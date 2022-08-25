package Qoobee;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime dateTime;

    public Deadline(String description, String by) {
        super(description);
        String[] curr = by.split(" ", 2);
        String[] date = curr[0].split("/", 3);
        String hour = curr[1].substring(0, 2);
        String minute = curr[1].substring(2, 4);
        this.dateTime = LocalDateTime.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]),
                Integer.parseInt(date[0]), Integer.parseInt(hour), Integer.parseInt(minute));
    }

    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
        return this.dateTime.format(formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + getDateTime() + ")";
    }
}