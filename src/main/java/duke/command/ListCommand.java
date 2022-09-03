package duke.command;

import duke.TaskList;

public class ListCommand extends Command {
    /**
     * Executes the command by printing the list.
     *
     * @param tasks The user's current list of tasks.
     */
    public String execute(TaskList tasks) {
        return tasks.toString();
    }
}
