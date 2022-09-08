package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command to set priority of a specific task from the task list.
 */
public class PriorityCommand extends Command {

    public static final String COMMAND_WORD = "priority";
    public static final String CHANGE_TASK_PRIORITY_MSG = "Noted. I've set the priority of this task to ";
    public static final String NO_TASK_MSG = "There is no task at this index";
    private int toChangePriority;
    private String priorityLevel;

    public PriorityCommand(int toChangePriority, String priorityLevel) {
        this.toChangePriority = toChangePriority - 1;
        this.priorityLevel = priorityLevel;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            tasks.changeTaskPriority(toChangePriority, priorityLevel);
            Task taskToChange = tasks.getTask(toChangePriority);
            return CHANGE_TASK_PRIORITY_MSG + taskToChange.getTaskPriority() + ":\n" + taskToChange.taskInfo();
        } catch (IndexOutOfBoundsException e) {
            return NO_TASK_MSG;
        }

    }
}
