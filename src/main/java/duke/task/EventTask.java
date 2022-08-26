package duke.task;

import java.time.LocalDateTime;

/**
 * A concrete class that represents an event task and implements its parent abstract class.
 */
public class EventTask extends Task {

    private static final String LABEL = "E";

    private final LocalDateTime dateTime;

    /**
     * The standard constructor.
     */
    public EventTask(String taskTitle, LocalDateTime dateTime) {
        super(taskTitle, TaskType.EVENT);
        this.dateTime = dateTime;
    }

    /**
     * Returns a string representation of the task, to be displayed on the screen.
     *
     * @return A string representation.
     */
    @Override
    public String toString() {
        return super.getStringRepresentation(
                LABEL,
                super.taskTitle + " at " + dateTime.format(Task.OUTPUT_FORMATTER)
        );
    }

    /**
     * Returns a string representation for file saving.
     *
     * @return A string that is suitable to be saved as plain text.
     */
    @Override
    public String getFileRepresentation() {
        return super.getFileRepresentation(LABEL, dateTime.format(Task.OUTPUT_FORMATTER));
    }
}
