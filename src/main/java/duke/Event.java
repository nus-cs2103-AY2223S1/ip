package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private final DateTimeFormatter FORMAT_MMDDYY_TIME = 
            DateTimeFormatter.ofPattern("MM/dd/yy, HH:mm");
    private LocalDateTime endTime;

    public Event(String taskName, LocalDateTime endTime) {
        super(taskName);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + 
                this.endTime.format(FORMAT_MMDDYY_TIME) + ")";
    }
}
