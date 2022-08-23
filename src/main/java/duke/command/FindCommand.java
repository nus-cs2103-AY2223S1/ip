package duke.command;

import duke.DukeException;
import duke.task.TasksList;

/**
 * Represents the command to find a task by searching for a keyword.
 */
public class FindCommand extends Command {
    private TasksList tasksList;
    private String[] command;

    /**
     * Creates FindCommand with the given TasksList and command.
     *
     * @param tasksList The TasksList to search the task from.
     * @param command The String array of the user's command.
     */
    public FindCommand(TasksList tasksList, String[] command) {
        this.tasksList = tasksList;
        this.command = command;
    }

    @Override
    public void execute() throws DukeException {
        tasksList.findTask(command);
    }

    /**
     * Checks if the command given refers to finding a task from the TasksList.
     *
     * @param s The command word (if any) from the user's input.
     * @return true if the command to find is valid, false otherwise.
     */
    public static boolean isCommand(String s) {
        return s.equals("find");
    }
}
