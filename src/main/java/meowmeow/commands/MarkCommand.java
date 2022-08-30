package meowmeow.commands;
import meowmeow.Storage;
import meowmeow.TaskList;
import meowmeow.Ui;

/**
 * <p>Class MarkCommand is a concrete class that implements the Command interface.</p>
 * <p>This class is used to mark a task as completed or unmark tasks in the task list.</p>
 * <p>This class is a concrete class because it has an implementation.</p>
 */
public class MarkCommand extends Command {

    private boolean isDone;
    private int taskNum;

    /**
     * Constructor for MarkCommand.
     * @param isDone true if the task is to be marked as completed, false if the task is to be marked as incomplete.
     * @param taskNum the number of the task to be marked.
     */
    public MarkCommand(boolean isDone, int taskNum) {
        this.isDone = isDone;
        this.taskNum = taskNum;
    }

    /**
     * Method that executes the MarkCommand.
     *
     * @param tasks the task list to be operated on by the command.
     *              The task list is used to mark the task as completed or incomplete.
     * @param ui the user interface to be used by the command.
     *           The user interface is used to display the result of the command.
     * @param storage the storage to be used by the command.
     *                The storage is used to save and load the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (isDone) {
            tasks.finishTask(taskNum);
        } else {
            tasks.unfinishTask(taskNum);
        }
    }

    /**
     * isExit method that returns false for MarkCommand.
     * @return false for MarkCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
