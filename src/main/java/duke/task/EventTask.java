package duke.task;

import java.time.LocalDateTime;

public class EventTask extends Task {

    private static final String LABEL = "E";

    private final LocalDateTime dateTime;

    public EventTask(String taskTitle, LocalDateTime dateTime) {
        super(taskTitle, TaskType.EVENT);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return super.getStringRepresentation(
                LABEL,
                super.taskTitle + " at " + dateTime.format(Task.OUTPUT_FORMATTER)
        );
    }

    @Override
    public String getFileRepresentation() {
        return super.getFileRepresentation(LABEL, dateTime.format(Task.OUTPUT_FORMATTER));
    }
}
