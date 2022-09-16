package duke.command;

import duke.duke.Duke;
import duke.duke.DukeException;
import duke.util.Storage;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/** Represents the command to add task that inherits from Command. */
public class AddCommand extends Command {
    /** Represents the input keyed by the user. */
    private final String userInput;

    /**
     * Represents a AddCommand object
     *
     * @param userInput string from the user
     */
    public AddCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        String command;
        String message = "";
        command = userInput.split(" ")[0];

        Task task;
        if (userInput.split(" ", 2).length == 1) {
            if (command.equals(Duke.Keyword.TODO.getKeyword())) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            } else if (command.equals(Duke.Keyword.EVENT.getKeyword())) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            } else if (command.equals(Duke.Keyword.DEADLINE.getKeyword())) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

        String taskDetails = userInput.split(" ", 2)[1];

        if (command.equals(Duke.Keyword.TODO.getKeyword())) {
            task = new Todo(taskDetails.split("/")[0]);
            if (taskDetails.split("/").length > 1) {
                task.setPriority(taskDetails.split("/")[1].split(" ", 2)[1]);
            }
            taskList.addTask(task);
        } else if (command.equals(Duke.Keyword.EVENT.getKeyword())) {
            task = new Event(taskDetails.split("/")[0],
                    taskDetails.split("/")[1].split(" ", 2)[1]);
            if (taskDetails.split("/").length > 2) {
                task.setPriority(taskDetails.split("/")[2].split(" ", 2)[1]);
            }
            taskList.addTask(task);
        } else if (command.equals(Duke.Keyword.DEADLINE.getKeyword())) {
            task = new Deadline(taskDetails.split("/")[0],
                    taskDetails.split("/")[1].split(" ", 2)[1]);
            if (taskDetails.split("/").length > 2) {
                task.setPriority(taskDetails.split("/")[2].split(" ", 2)[1]);
            }
            taskList.addTask(task);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        message += "Got it. I've added this task:\n";
        message += "\t" + task.toString() + "\n";
        message += "Now you have " + taskList.getTaskListSize() + " tasks in the list.\n";

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
