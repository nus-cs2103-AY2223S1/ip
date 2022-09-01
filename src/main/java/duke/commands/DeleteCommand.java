package duke.commands;

import duke.others.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;

/**
 * Represents a command that deletes a task from the task list.
 * It deletes the task based on the provided index of the task in the list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_SUCCESS = "Noted. I've removed this duke.task:\n ";
    private int index;

    /**
     * Constructs a new DeleteCommand instance.
     *
     * @param index Index of the task in the task list.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by deleting the task with given index in the task list.
     * Writes the updated result to the local disk.
     * Tells the user if the provided index is invalid.
     *
     * @param tasks Task List that stores tasks.
     * @param storage Storage in charge of writing to the local disk.
     * @return A string showing a message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            Task task = tasks.findTask(this.index);
            tasks.delete(task);
            String successMessage = MESSAGE_SUCCESS + task.toString()
                    + "\n" + tasks.getCount();
            storage.overwriteFile(tasks);
            return successMessage;
        } catch (IndexOutOfBoundsException e) {
            return "☹ Please enter an index in the range!";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Keeps the programme running.
     *
     * @return True.
     */
    @Override
    public boolean isRunning() {
        return true;
    }
}
