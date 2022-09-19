package duke.command;

import duke.Storage;
import duke.task.TaskList;

/**
 * Class which manages unmarking tasks.
 */
public class UnmarkCommand extends Command {

    private String number;

    public UnmarkCommand(String command) {
        number = command;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            int index = Integer.parseInt(number);
            tasks.unmark(index);
            storage.unmark(index);
            return "Got it! Task " + index + " has not yet been completed:\n  " + tasks.showTask(index);
        } catch (NumberFormatException e) {
            return "You need to provide a task's index to unmark!";
        } catch (IndexOutOfBoundsException e) {
            return "You've given me an invalid task to unmark!";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
