package duke.command;

import duke.TaskList;
import duke.UI;

public class ListCommand extends Command {
    /**
     * Executes the command by printing the list.
     *
     * @param tasks The user's current list of tasks.
     */
    public void execute(TaskList tasks) {
        UI.printList(tasks);
    }
}
