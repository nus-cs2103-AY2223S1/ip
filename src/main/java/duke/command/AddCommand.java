package duke.command;

import duke.task.Task;
import duke.task.TaskList;

/**
 * This class encapsulates an add command from the user.
 */
public class AddCommand extends Command {
    private TaskList taskList;
    private Task task;

    /**
     * Creates an AddCommand with the given TaskList and Task.
     *
     * @param taskList The TaskList.
     * @param task     The Task.
     */
    public AddCommand(TaskList taskList, Task task) {
        this.taskList = taskList;
        this.task = task;
    }

    /**
     * Adds the Task into the TaskList.
     *
     * @return A String signalling that the Task has been added.
     */
    @Override
    public String execute() {
        this.taskList.add(this.task);
        return "I've added this task:\n  "
                + this.task
                + "\nNow you have " + this.taskList.size() + " tasks in your list.";
    }
}
