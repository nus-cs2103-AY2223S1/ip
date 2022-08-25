package duke.task;

import duke.Command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A concrete implementation of {@code Event}
 */
public class Event extends Task {
    // original access modifier was protected
    private final LocalDateTime dateTime;

    /**
     * Constructs an {@code Event}
     * @param description The description of the {@code Task}
     * @param taskCommand The {@code Command} type of the {@code Task}
     * @param dateTime The date and time for the {@code Task}
     */
    public Event(String description, Command taskCommand, LocalDateTime dateTime) {
        super(description, taskCommand);
        this.dateTime = dateTime;
    }

    @Override
    public String getFileStorageString(int index) {
        return taskCommand.getString() + " " + description + " /at " + dateTime.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\n" + getTaskDoneString(index);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
                + ")";
    }
}
