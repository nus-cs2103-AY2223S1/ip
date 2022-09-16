package duke.command;

import duke.duke.DukeException;
import duke.util.Storage;
import duke.task.TaskList;

/** Represents the command to find tasks with given keyword that inherits from Command. */
public class FindCommand extends Command {
    /** Represents the input keyed by the user. */
    private final String userInput;

    /** Represents a FindCommand object */
    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        String message = "";

        if (userInput.split(" ").length == 1) {
            throw new DukeException("OOPS!!! The find command cannot have a missing query.");
        }
        String query = userInput.split(" ")[1];
        message += "Here are the matching tasks in your list:\n";

        message += taskList.findTaskThatContains(query);

        return message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
