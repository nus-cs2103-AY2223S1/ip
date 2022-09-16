package zeus.command;

import zeus.Ui;
import zeus.ZeusException;
import zeus.storage.Storage;
import zeus.task.Task;
import zeus.task.TaskList;

/**
 * Represents a command to untag a <code>Task</code>.
 *
 * @author Derrick Khoo
 */
public class UntagCommand extends Command {
    private int index;

    /**
     * Constructs a command to untag a <code>Task</code>.
     *
     * @param index the index of the task in the list of tasks
     */
    public UntagCommand(int index) {
        this.index = index;
    }
    /**
     * Handles the command to tag a <code>Task</code>.
     *
     * @param storage  the <code>Storage</code> dealing with loading and saving tasks in a file
     * @param ui       the user interfaces that deals with user inputs
     * @param taskList the list of tasks
     * @throws ZeusException if there is an error marking the task as done
     */
    public String handle(Storage storage, Ui ui, TaskList taskList) throws ZeusException {
        Task t = taskList.getTask(index);
        String assignedTag = t.getTag();
        if (assignedTag.isEmpty()) {
            throw new ZeusException("The task is already not tagged by default, you cant untag nothing!");
        }
        assert !assignedTag.isBlank();
        t.untagTask();
        storage.saveData(taskList);
        return "Zeus says:\n" + ui.formatMessage("Great, I've untagged the task for you.");
    }
}
