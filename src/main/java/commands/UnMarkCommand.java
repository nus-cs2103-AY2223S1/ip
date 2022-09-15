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

    /**
     * Executes UnMarkCommand by marking chosen task as incomplete and prints confirmation.
     *
     * @param taskList Task list containing task to be unmarked.
     * @param ui Ui to retrieve return string from.
     * @param s Storage containing the list of tasks or where tasks should be saved.
     * @return String containing confirmation message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage s) {
        taskList.unmark(index);
        String str = "okies!\n" + "  " + taskList.retrieveTask(index).toString()
                + "\nhas been marked as not done yet!\n";
        return str;
    }
}
