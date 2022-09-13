package duke.command;

import duke.Storage;
import duke.tasklist.TaskList;

/**
 * Concrete class representing FIND
 */
public class FindCommand extends CommandWithTasklistAndCommands {
    /**
     * @param arguments The arguments
     */
    public FindCommand(String[] arguments) {
        super(arguments);
    }

    @Override
    public String execute(Storage storage, TaskList taskList) {
        return ("Here are the matching tasks in your list:"
                + taskList.getTextRepresentationOfTasksMatchingKeyword(arguments[0]));
    }
}

