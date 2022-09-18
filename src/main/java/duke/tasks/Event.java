package duke.tasks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task{
    private LocalDateTime time;

    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    /** Returns formatted String to write back to our data
     *
     * @return String to save to our stored data
     */
    @Override
    public String writeToFile() {
        return "E|" + super.writeToFile() + "|at "
                + time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " ("
                + time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
    }
}