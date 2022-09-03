package duke.command;

import duke.task.TaskList;

/**
 * Represents a find command
 */
public class FindCommand implements Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Find tasks in the task list. Parameters: DESCRIPTION. Example: " + COMMAND_WORD + " book";

    private String description;

    /**
     * Constructor for a {@link FindCommand}
     *
     * @param description Search term to find tasks
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Executes a command
     *
     * @param taskList
     */
    @Override
    public String execute(TaskList taskList) {
        TaskList filteredList = taskList.findTask(description);
        assert filteredList != null : "Filtered list cannot be null";
        return filteredList.printListWithMessage("Here are the matching tasks in your list:");
    }
}
