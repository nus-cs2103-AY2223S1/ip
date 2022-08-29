package duke.command;

import duke.FileStorage;
import duke.task.TaskList;


/**
 * Command used to mark a particular task in the taskList as uncompleted.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
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
     */
    @Override
    public String execute(TaskList list, FileStorage storage) {
        list.markTaskUndone(index);
        storage.writeToFile(list.getList());
        return String.format("OK, I've marked this task as not done yet:\n  %s", list.retrieveTask(index));
    }
}
