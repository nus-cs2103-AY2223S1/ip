package duke.task;

import java.time.LocalDateTime;

/**
 * A concrete class that represents a deadline task and implements its parent abstract class.
 */
public class DeadlineTask extends Task {

    private static final String LABEL = "D";

    private final LocalDateTime deadline;

    /**
     * The standard constructor.
     */
    public DeadlineTask(String taskTitle, LocalDateTime deadline) {
        super(taskTitle, TaskType.DEADLINE);
        this.deadline = deadline;
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
                super.taskTitle + " by " + deadline.format(Task.OUTPUT_FORMATTER)
        );
    }

    /**
     * Returns a string representation for file saving.
     *
     * @return A string that is suitable to be saved as plain text.
     */
    @Override
    public String getFileRepresentation() {
        return super.getFileRepresentation(LABEL, deadline.format(Task.OUTPUT_FORMATTER));
    }
}
