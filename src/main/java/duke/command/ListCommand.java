package duke.command;

import duke.DukeException;
import duke.util.Ui;

/**
 * Command to list all {@code Task}s in the {@code TaskList}.
 */
public class ListCommand extends Command {

    /**
     * Returns a {@code String} containing all {@code Task}s in the {@code TaskList}.
     *
     * @return Message {@code String} from command execution.
     */
    @Override
    public String execute() throws DukeException {
        String[] tasks = Command.taskList.getAllTasksInDisplayFormat().toArray(new String[0]);
        if (tasks.length == 0) {
            return Ui.formatMessages(new String[]{"No tasks"});
        } else {
            return Ui.formatMessages(tasks);
        }
    }
}
