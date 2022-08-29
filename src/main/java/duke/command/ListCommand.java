package duke.command;

import duke.task.TaskList;

/**
 * Represents the command to list all the tasks in the given TaskList.
 */
public class ListCommand extends Command {
    private TaskList taskList;

    /**
     * Creates ListCommand with the given TaskList.
     *
     * @param taskList The TaskList to list the tasks from.
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute() {
        return this.taskList.listTasks();
    }

    /**
     * Checks if the command given refers to listing all the tasks from the TaskList.
     *
     * @param s The command word (if any) from the user's input.
     * @return true if the command to list is valid, false otherwise.
     */
    public static boolean isCommand(String s) {
        return s.equals("list");
    }
}
