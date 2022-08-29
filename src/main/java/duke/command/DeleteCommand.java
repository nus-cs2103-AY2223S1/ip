package duke.command;

import duke.task.TaskList;

public class DeleteCommand implements Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete a task. Parameters: INDEX. Example: "
            + COMMAND_WORD + " 2";

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.remove(index - 1);
    }
}
