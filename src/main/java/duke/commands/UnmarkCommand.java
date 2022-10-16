package duke.commands;

import duke.DukeException;
import duke.task.Task;
import duke.TaskList;
import duke.Storage;

/**
 * A <code>Command</code> that handles the marking of a <code>Task</code> (as not done)
 */
public class UnmarkCommand extends Command {
    private final String[] arguments;

    /**
     * A constructor for the <code>UnmarkCommand</code> class
     *
     * @param args Arguments from user input
     */
    public UnmarkCommand(String[] args) {
        this.arguments = args;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(Storage storage, TaskList tl) throws DukeException, AssertionError {
        if (arguments.length < 2) {
            throw new DukeException("You did not state a task number!");
        } else if (arguments.length > 2) {
            throw new DukeException("You can only state one task number!");
        }

        String response;
        try {
            int taskNum = Integer.parseInt(arguments[1]) - 1;
            assert taskNum > -1 : "The task number should be a valid number (starting from 1)";
            Task unmarkedTask = tl.unmarkTask(taskNum);
            response = "Okay, I have marked this task as not done:\n  "
                    + unmarkedTask.toString();
            storage.refreshList(tl.getTasks());
        } catch (IndexOutOfBoundsException indexErr) {
            response = "This task number does not exist in the list!";
        } catch (NumberFormatException parseErr) {
            response = "Is that even a number?! Try entering an actual number next time";
        }
        return response;
    }
}
