package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final DateTimeFormatter FORMAT_TIME_YYYYMMDD = 
            DateTimeFormatter.ofPattern("HH:mm 'on' yyyy/MM/dd");
    private LocalDateTime deadLine;

    public Deadline(String taskName, LocalDateTime deadLine) {
        super(taskName);
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + 
                this.deadLine.format(FORMAT_TIME_YYYYMMDD) + ")";
    }
}
