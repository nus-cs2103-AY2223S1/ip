package Sakura;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime dateFormat = LocalDateTime.parse(by, formatter);
        this.by = dateFormat;
    }

    @Override
    public String stringifyTask() {
        String timeFormat = this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return String.format("D|%d|%s|%s", this.isDone ? 1 : 0, this.description, timeFormat);
    }

    @Override
    public String toString() {
        String timeFormat = this.by.format(DateTimeFormatter.ofPattern("HH:mm, MMM dd yyyy"));
        return "\u001B[31m(DEADLINE)\u001B[0m" + super.toString() + " (by: " + timeFormat + ")";
    }
}
