package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class Deadline extends Task  {
    private LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    LocalDateTime getTime() {
        return this.deadline;
    }

    /** Returns formatted String to write back to our data
     *
     * @return String to save to our stored data
     */
    @Override
    String writeToFile() {
        return "D|" + super.writeToFile() + "|by " + deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
    }
}