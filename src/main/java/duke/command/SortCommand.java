package duke.command;

import duke.task.TaskList;

/**
 * Represents a sort command
 */
public class SortCommand implements Command {
    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort deadlines in Duke. Example: " + COMMAND_WORD;

    /**
     * Executes a command
     *
     * @param taskList
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.sortDeadlines().toString();
    }
}
