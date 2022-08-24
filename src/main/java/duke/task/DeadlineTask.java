package duke.task;

import duke.Duke;
import duke.util.CommandParser;

public class DeadlineTask extends Task {

    private static final String LABEL = "D";

    private String deadline;

    protected DeadlineTask(String taskTitle, String deadline) {
        super(taskTitle, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.getStringRepresentation(LABEL, super.taskTitle + " by " + deadline);
    }
}
