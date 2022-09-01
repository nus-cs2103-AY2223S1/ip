package duke.commands;

import duke.others.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;

/**
 * Represents a command that marks the given task as done.
 * It marks the task based on the provided index of the task in the task list.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_SUCCESS = "Nice! I've marked this duke.task as done:\n ";
    private int index;

    /**
     * Constructs a new MarkCommand instance.
     *
     * @param index Index of the task in the task list.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the task with the given index
     * stored in the task list as done.
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
            task.markAsDone();
            String successMessage = MESSAGE_SUCCESS + task.toString();
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
