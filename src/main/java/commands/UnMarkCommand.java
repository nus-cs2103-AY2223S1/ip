package commands;

import duke.Storage;
import duke.Ui;
import tasks.TaskList;

/**
 * UnMarkCommand marks the chosen task as incomplete.
 */
public class UnMarkCommand extends Command {
    public int index;

    /**
     * Constructor for UnMarkCommand.
     *
     * @param index Index of task to be unmarked.
     */
    public UnMarkCommand(int index) {
        this.index = index;
    }

    @Override
    /**
     * Executes UnMarkCommand by marking chosen task as incomplete and prints confirmation.
     *
     * @param taskList Task list containing task to be unmarked.
     */
    public String execute(TaskList taskList, Ui ui, Storage s) {
        taskList.unmark(index);
        String str = "OK, I've marked this task as not done yet:\n" + "  "
                + taskList.retrieveTask(index).toString() + "\n";
        return str;
    }
}
