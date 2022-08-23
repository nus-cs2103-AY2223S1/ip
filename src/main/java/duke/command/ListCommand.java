package duke.command;

import duke.task.TasksList;

/**
 * Represents the command to list all the tasks in the given TasksList.
 */
public class ListCommand extends Command {
    private TasksList tasksList;

    /**
     * Creates ListCommand with the given TasksList.
     *
     * @param tasksList The TasksList to list the tasks from.
     */
    public ListCommand(TasksList tasksList) {
        this.tasksList = tasksList;
    }

    @Override
    public void execute() {
        this.tasksList.listTasks();
    }

    /**
     * Checks if the command given refers to listing all the tasks from the TasksList.
     *
     * @param s The command word (if any) from the user's input.
     * @return true if the command to list is valid, false otherwise.
     */
    public static boolean isCommand(String s) {
        return s.equals("list");
    }
}
