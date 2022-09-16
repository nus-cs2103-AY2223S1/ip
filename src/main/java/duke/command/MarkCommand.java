package duke.command;

import duke.duke.DukeException;
import duke.util.Storage;
import duke.task.TaskList;
import duke.task.Task;

/**
 * Represents the command to mark specific Tasks in TaskList as completed or not completed
 * that inherits from Command.
 */
public class MarkCommand extends Command {
    /** Represents the input keyed by the user. */
    private final String userInput;
    /** Denotes if the command marks or unmarks a task. */
    private final boolean isMark;

    /**
     * Represents a MarkCommand object, if isMark is true command marks the
     * task in TaskList else it unmarks the task in TaskList.
     *
     * @param userInput string from the user.
     * @param isMark input from user that determines the task is going to be marked or unmarked.
     */
    public MarkCommand(String userInput, boolean isMark) {
        this.userInput = userInput;
        this.isMark = isMark;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        String message = "";

        if (userInput.split(" ").length == 1) {
            throw new DukeException("OOPS!!! The mark/unmark command cannot have a missing index.");
        }
        String index = userInput.split(" ")[1];
        Task task = taskList.updateTaskStatus(Integer.parseInt(index), isMark);
        if (isMark) {
            message += "Nice! I've marked this task as done:\n";
        } else {
            message += "OK, I've marked this task as not done yet:\n";
        }
        message += task.toString() + "\n";
        storage.saveTaskList(taskList);
        return message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
