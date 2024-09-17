package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command to mark a specific task as done in the task list.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String MARK_TASK_MSG = "Nice! I've marked this task as done:\n ";
    public static final String NO_TASK_MSG = "Hmm, I can't find this hole number, enter a valid integer number";
    private int toMark;

    /**
     * Constructor for the MarkCommand Object.
     * 
     * @param toMark The index of the task to be mark as done.
     */
    public MarkCommand(int toMark) {
        this.toMark = toMark - 1;
    }

    /**
     * Marks the specific task as done in the task list and return a message that the task
     * has been mark as done.
     *
     * @param task The TaskList object of the chatbot.
     * @param storage The storage object of the chatbot.
     * @return The message that the task has been mark as done if successfully mark as done.
     */
    @Override
    public String execute(TaskList task, Storage storage) {
        try {
            task.markTask(this.toMark);
            Task taskToMark = task.getTask(toMark);
            assert taskToMark == null : "Task to be mark done should not be empty";
            return MARK_TASK_MSG + taskToMark.taskInfo();
        } catch (IndexOutOfBoundsException e) {
            return NO_TASK_MSG;
        }

    }

}
