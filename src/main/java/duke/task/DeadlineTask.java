package duke.task;

import java.time.LocalDateTime;

public class DeadlineTask extends Task {

    private static final String LABEL = "D";

    private final LocalDateTime deadline;

    public DeadlineTask(String taskTitle, LocalDateTime deadline) {
        super(taskTitle, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.getStringRepresentation(
                LABEL,
                super.taskTitle + " by " + deadline.format(Task.OUTPUT_FORMATTER)
        );
    }

    @Override
    public String getFileRepresentation() {
        return super.getFileRepresentation(LABEL, deadline.format(Task.OUTPUT_FORMATTER));
    }
}
