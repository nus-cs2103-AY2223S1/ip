package duke.command;

import duke.duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;

/** Represents the command to change the priority level of Tasks in TaskList that inherits from Command. */
public class PriorityCommand extends Command {
    /** Represents the input keyed by the user. */
    private final String userInput;

    /**
     * Represents a PriorityCommand object
     *
     * @param userInput string from the user
     */
    public PriorityCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        String message = "";

        if (userInput.split(" ").length < 3) {
            throw new DukeException("OOPS!!! The priority command cannot have empty parameters. "
                    + "Example command is priority 1 high");
        }
        String index = userInput.split(" ")[1];
        String priorityString = userInput.split(" ")[2];
        Task task = taskList.updateTaskPriority(Integer.parseInt(index), priorityString);
        message += "OK, I've set the priority of the task to " + task.getPriorityString() + "\n";
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
