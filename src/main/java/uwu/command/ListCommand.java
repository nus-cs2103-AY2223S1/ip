package uwu.command;

import uwu.task.TaskList;
import uwu.uwu.Storage;
import uwu.uwu.Ui;

/**
 * Lists all the tasks in the task list.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": prints out all of your tasks.";

    /**
     * Executes the ListCommand which is to list all tasks stored in user's hard disk.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out UwuBot's response.
     * @param storage The task list that is stored in the user's hard disk.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.displayTasks(tasks);
    }

    /**
     * Returns whether ListCommand exits the program.
     *
     * @return false as ListCommand does not exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
