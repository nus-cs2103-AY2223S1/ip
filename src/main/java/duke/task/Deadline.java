package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate date;
    private LocalTime time;

    public Deadline(String content, LocalDate date, LocalTime time) {
        super(content);
        this.date = date;
        this.time = time;
    }

    @Override 
    public String toString() {
        return String.format("[D]%s (by: %s %s)", super.toString(), 
                this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")), 
                        this.time.format(DateTimeFormatter.ofPattern("hh:mm a")));
    }

    @Override
    public String toFileData() {
        return String.format("D | %d | %s | %s %s", this.status ? 1 : 0, this.content, 
                this.date.toString(), this.time.toString());
    }
}
