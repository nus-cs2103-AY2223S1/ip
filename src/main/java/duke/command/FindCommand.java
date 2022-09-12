package duke.command;

import duke.TaskList;

/**
 * Concrete class representing FIND
 */
public class FindCommand extends CommandWithTasklistAndCommands {
    /**
     * @param taskList  The tasks list
     * @param arguments The arguments
     */
    public FindCommand(TaskList taskList, String[] arguments) {
        super(taskList, arguments);
    }

    @Override
    public String execute() {
        return ("Here are the matching tasks in your list:"
                + taskList.getTextRepresentationOfKeywordTasks(arguments[0]));
    }
}

