package duke.command;

import duke.Storage;
import duke.task.TaskList;

/**
 * Class which manages invalid commands.
 */
public class InvalidCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "You've entered an invalid input!";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
