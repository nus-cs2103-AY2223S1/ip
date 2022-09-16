package byu.task;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import byu.util.Ui;

/**
 *  A task that takes place over a specified period of time.
 */
public class Event extends Task {

    public static final String SYMBOL = "E";
    private static final String PRINT_FORMAT = "[E]%s (at: %s to %s)";
    private static final String WRITE_FORMAT = "E | %s | %s | %s | %s\n";

    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    /**
     * Creates an event, which is a task that takes place over a specified period of time.
     */
    public Event(String name, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(name);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public void write(FileWriter fileWriter) throws IOException {
        String line = String.format(Event.WRITE_FORMAT, this.getDoneValue(), this.getName(),
                this.startDateTime, this.endDateTime);
        fileWriter.write(line);
    }

    @Override
    public String toString() {
        return String.format(Event.PRINT_FORMAT, super.toString(),
                this.startDateTime.format(Ui.PRINT_DATE_TIME_FORMATTER),
                this.endDateTime.format(Ui.PRINT_DATE_TIME_FORMATTER));
    }

    @Override
    public boolean equals(Task task) {
        if (task == null) {
            return false;
        } else if (!(task instanceof Event)) {
            return false;
        } else if (!this.getName().equals(task.getName())) {
            return false;
        } else {
            // to compare the periods of the two events
            Event event = (Event) task;
            boolean isStartDateTimeSame = this.startDateTime.equals(event.startDateTime);
            boolean isEndDateTimeSame = this.endDateTime.equals(event.endDateTime);
            return isStartDateTimeSame && isEndDateTimeSame;
        }
    }
}
