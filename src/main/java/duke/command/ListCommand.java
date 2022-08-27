package duke.command;

import duke.FileStorage;
import duke.task.TaskList;

/**
 * Command used to list all tasks in the taskList.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    /**
     * Lists all tasks from the taskList.
     *     and returns the corresponding message to the GUI.
     * @param list The taskList of Duke.
     * @param storage The fileStorage of Duke.
     */
    @Override
    public String execute(TaskList list, FileStorage storage) {
        StringBuilder strBuilder = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < list.getListSize(); i++) {
            strBuilder.append("\n").append(i + 1).append(".").append(list.retrieveTask(i));
        }
        return strBuilder.toString();
    }
}
