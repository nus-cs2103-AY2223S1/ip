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

    /**
     * Constructor for the PriorityCommand Object.
     *
     * @param toChangePriority The index of the task to change its priority level.
     * @param priorityLevel The priority level to change to.
     */
    public PriorityCommand(int toChangePriority, String priorityLevel) {
        this.toChangePriority = toChangePriority - 1;
        this.priorityLevel = priorityLevel;
    }

    /**
     * Change the priority level of the specific task in the task list and return a message that the
     * priority of the task has been changed.
     *
     * @param tasks The TaskList object of the chatbot.
     * @param storage The storage object of the chatbot.
     * @return The message that the priority of the task has been changed.
     */
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
