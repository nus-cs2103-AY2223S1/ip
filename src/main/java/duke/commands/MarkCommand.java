package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;

/**
 * Represents a command that marks the given task as done.
 * It marks the task based on the provided index of the task in the task list.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_SUCCESS = "Alright, I've marked this task as done:\n ";
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
            String errorMessage = "Are you blind? Enter an index in the range!";
            return errorMessage;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Checks if a given object is equal to a MarkCommand instance.
     *
     * @param object Given object.
     * @return True or false.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof MarkCommand) {
            MarkCommand markCommand = (MarkCommand) object;
            return this.index == markCommand.index;
        } else {
            return false;
        }
    }

}
