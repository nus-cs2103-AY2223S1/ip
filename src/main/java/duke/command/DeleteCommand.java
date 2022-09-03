package duke.command;

import duke.task.TaskList;

/**
 * Represents a delete command
 */
public class DeleteCommand implements Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete a task. Parameters: INDEX. Example: "
            + COMMAND_WORD + " 2";

    private int index;

    /**
     * Constructor for a {@link DeleteCommand}
     *
     * @param index Index for the task in the task list
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes a command
     *
     * @param taskList
     */
    @Override
    public String execute(TaskList taskList) {
        try {
            return taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            return "Invalid task index";
        }
    }
}
