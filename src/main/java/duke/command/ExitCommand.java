package duke.command;

import duke.Storage;
import duke.task.TaskList;

/**
 * Represents the exit command to say bye to the user.
 */
public class ExitCommand extends Command {
    /**
     * Returns true because this is the exit command.
     *
     * @return true.
     */
    public boolean isExit() {
        return true;
    }

    /**
     * Says bye to user.
     *
     * @param tasks List of tasks.
     * @param storage Storage for Duke's file operations.
     * @return Duke's response.
     */
    public String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }
}
