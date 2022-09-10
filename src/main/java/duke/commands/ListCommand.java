package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Represents a list command.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Lists the current tasks in the task list.
     *
     * @param tasks The list of tasks in Duke.
     * @param ui The TextUi class used to print message in Duke.
     * @param storage The storage used to save the tasks in the local file.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            // Display duke.task as 1-index
            str.append(i + 1).append(".").append(tasks.getTask(i)).append("\n");
        }
        ui.printTextWithDivider(str.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
