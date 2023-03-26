package duke.command;

import duke.main.TaskList;
import duke.task.Task;

/**
 * Abstract class for Command classes which makes a type of command.
 */
public abstract class MakeTaskCommand extends Command {
    /**
     * Checks if a certain task is already a duplicate of one in the tasklist.
     * @param task The task to be checked.
     * @param taskList The taskList which contains the list to be checked against.
     * @return Whether the new task is a duplicate.
     */
    protected boolean isDuplicate(Task task, TaskList taskList) {
        return taskList.getTaskList().stream().anyMatch(x -> x.equals(task));
    }
}
