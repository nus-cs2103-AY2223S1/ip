package duke.command;

import duke.FileStorage;
import duke.Ui;
import duke.task.TaskList;


/**
 * Command used to mark a particular task in the taskList as uncompleted.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String HELP_MESSAGE = "Unmark Task Done: unmark [index]\n\n";
    private int index;
    public UnmarkCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Marks the task from the taskList as uncompleted, saves to file
     * and prints out the corresponding message to the GUI.
     *
     * @param list The taskList of Duke.
     * @param storage The fileStorage of Duke.
     * @param ui The Ui of Duke.
     * @return The message meant for the GUI.
     */
    @Override
    public String execute(TaskList list, FileStorage storage, Ui ui) {
        list.markTaskUndone(index);
        storage.writeToFile(list.getList());
        return ui.getMarkUndoneMessage(list.retrieveTask(index));
    }
}
