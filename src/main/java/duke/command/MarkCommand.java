package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * MarkCommand is the command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int num;

    /**
     * Constructor for MarkCommand.
     *
     * @param num Number of the task to be marked as done.
     */
    public MarkCommand(int num) {
        this.num = num;
    }

    /**
     * Executes the specific command corresponding to the type of input the user gives.
     *
     * @param list List of tasks.
     * @param ui Ui to print messages.
     * @param storage To save the list after making changes.
     * @return String that matches the command input.
     */
    @Override
    public String execCommand(TaskList list, Ui ui, Storage storage) {
        list.getTask(this.num).markAsDone();
        storage.saveList(list.save());
        return ui.showMark(list.getTask(this.num));
    }
}
