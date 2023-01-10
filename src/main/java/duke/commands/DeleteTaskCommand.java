package duke.commands;

import duke.core.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A command to delete a task from a TaskList.
 */
public class DeleteTaskCommand extends TaskListIndexCommand {

    /**
     * The text that is displayed when a task is removed.
     */
    public static final String REMOVE_TASK_TEXT = "Noted. I've removed this task:";

    public DeleteTaskCommand(String invoker, TaskList taskList) {
        super(invoker, taskList);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param t Task to perform an action on.
     * @return String output for the deletion message.
     * @throws DukeException
     */
    @Override
    public String execute(Task t) throws DukeException {
        this.taskList.removeTask(t);
        return REMOVE_TASK_TEXT + "\n  " + t + "\n" + taskCountText();
    }
}
