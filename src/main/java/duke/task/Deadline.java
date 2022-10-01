package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.command.CommandType;

/**
 *
 */
public class Deadline extends Task {
    private final LocalDateTime dateTime;

    /**
     * @param description The description of the {@code Task}
     * @param taskCommandType The {@code CommandType} type of the {@code Task}
     * @param dateTime    The date and time for the {@code Task}
     */
    public Deadline(String description, CommandType taskCommandType, LocalDateTime dateTime) {
        super(description, taskCommandType);
        this.dateTime = dateTime;
    }

    @Override
    public String getFileStorageString(int index) {
        return taskCommandType.getString() + " " + description + " /by " + dateTime.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\n" + getTaskDoneString(index);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
                + ")";
    }
}
