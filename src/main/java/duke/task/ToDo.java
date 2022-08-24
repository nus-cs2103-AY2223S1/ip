package duke.task;

import duke.Command;

public class ToDo extends Task {
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
