package pikachu.command;

import pikachu.PikachuException;
import pikachu.Storage;
import pikachu.Ui;
import pikachu.task.Task;
import pikachu.TaskList;

/**
 * Represents command that lists the current task list. A <code>ListCommand</code> object corresponds to
 * an instruction to list the current task list e.g., <code>list</code>
 */
public class ListCommand extends Command{

    /**
     * Lists the current task list
     * Informs the user about the situation through String output
     *
     * @param tasks Task List of all tasks currently.
     * @param ui Ui for user to see.
     * @param storage Storage in charge of the current tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder output = new StringBuilder();
        output.append("PikaPika (Nothing in the list)\n");
        for (Task task: tasks.taskList) {
            output.append(tasks.taskList.indexOf(task)+1).append('.').append(task).append('\n');
        }
        output.deleteCharAt(output.length() - 1);
        String.valueOf(output);
        System.out.println(String.valueOf(output));
    }

    /**
     * Returns whether this function performs an exit action on the task manager
     * @return false, do not exit.
     */
    public boolean isExit() {
        return false;
    }
    
}
