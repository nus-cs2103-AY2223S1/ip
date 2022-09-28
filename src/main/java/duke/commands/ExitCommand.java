package duke.commands;

import duke.task.TaskList;
import duke.task.TaskStorage;
import duke.util.Ui;

/**
 * Represents the class that return the goodbye message
 */

public class ExitCommand extends Command {
    public ExitCommand(TaskStorage storage, TaskList taskList, Ui ui) {
        super(storage, taskList, ui);
    }

    /**
     * @return the Goodbye message from Duke
     */
    public String sayGoodbye() {
        String result;
        result =  ui.sayGoodbye();
        return result;
    }
}
