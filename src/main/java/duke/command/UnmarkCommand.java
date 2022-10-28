package duke.command;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a unmark command
 */
public class UnmarkCommand implements Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Mark a task as not done. Parameters: INDEX. Example: "
            + COMMAND_WORD + " 2";

    private int index;

    /**
     * Constructor for a {@link UnmarkCommand}
     *
     * @param index Index for the task in task list
     */
    public UnmarkCommand(int index) {
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
            Task task = taskList.get(this.index);
            task.markNotDone();
            return String.format("OK, I've marked this task as not done yet:\n%s", task.toString());
        } catch (IndexOutOfBoundsException e) {
            return "Invalid task index";
        }
    }
}
