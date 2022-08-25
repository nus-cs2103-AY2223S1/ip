package duke.task;

import duke.Command;

/**
 * Represents the {@code ToDo} task
 */
public class ToDo extends Task {
    /**
     * Creates a {@code Task} with the specified description and of the specified type
     * @param description A description of the {@code Task}
     * @param taskCommand The {@code Command} that the task pertains to
     */
    public ToDo(String description, Command taskCommand) {
        super(description, taskCommand);
    }

    @Override
    public String getFileStorageString(int index) {
        return taskCommand.getString() + " " + description + "\n" + getTaskDoneString(index);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
