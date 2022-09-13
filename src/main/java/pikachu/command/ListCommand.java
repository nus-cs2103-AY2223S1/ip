package pikachu.command;

import pikachu.Storage;
import pikachu.TaskList;
import pikachu.Ui;
import pikachu.task.Task;

/**
 * Represents command that lists the current task list. A <code>ListCommand</code> object corresponds to
 * an instruction to list the current task list e.g., <code>list</code>
 */
public class ListCommand extends Command {

    /**
     * Lists the current task list.
     * Informs the user about the situation through String output.
     *
     * @param tasks Task List of all tasks currently.
     * @param ui Ui for user to see.
     * @param storage Storage in charge of the current tasks.
     * @return Pikachu's reply.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder output = new StringBuilder();
        output.append("PikaPika: \n");
        if (tasks.getTaskList().isEmpty()) {
            output.append("You have completed all the tasks!");
            return String.valueOf(output);
        }
        for (Task task: tasks.getTaskList()) {
            output.append(tasks.getTaskList().indexOf(task) + 1).append('.').append(task).append('\n');
        }
        output.deleteCharAt(output.length() - 1);
        return String.valueOf(output);
    }

    /**
     * Returns whether this function performs an exit action on the task manager.
     * @return false, do not exit.
     */
    public boolean isExit() {
        return false;
    }
}
