package raiden.command;

import raiden.RaidenException;
import raiden.task.TaskList;

/**
 * Represents the command to find a task by searching for a keyword.
 */
public class FindCommand extends Command {
    private TaskList taskList;
    private String[] command;

    /**
     * Creates FindCommand with the given TaskList and command.
     *
     * @param taskList The TaskList to search the task from.
     * @param command The String array of the user's command.
     */
    public FindCommand(TaskList taskList, String[] command) {
        this.taskList = taskList;
        this.command = command;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() throws RaidenException {
        return this.taskList.findTask(command) + "\n";
    }

    /**
     * Checks if the command given refers to finding a task from the TaskList.
     *
     * @param s The command word (if any) from the user's input.
     * @return true if the command to find is valid, false otherwise.
     */
    public static boolean isCommand(String s) {
        return s.equals("find");
    }
}
