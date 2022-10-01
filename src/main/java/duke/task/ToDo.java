package duke.task;

import duke.command.CommandType;

/**
 * Represents the {@code ToDo} task
 */
public class ToDo extends Task {
    /**
     * Creates a {@code Task} with the specified description and of the specified type
     * @param description A description of the {@code Task}
     * @param taskCommandType The {@code CommandType} that the task pertains to
     */
    public ToDo(String description, CommandType taskCommandType) {
        super(description, taskCommandType);
    }

    @Override
    public String getFileStorageString(int index) {
        return taskCommandType.getString() + " " + description + "\n" + getTaskDoneString(index);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
