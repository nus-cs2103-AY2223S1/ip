package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.gui.Response;
import duke.gui.ResponseType;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;

/**
 * Represents a command that adds a specific task type to a specified task list based on
 * the user's input upon its execution.
 */
public class AddTaskCommand extends Command {
    /** The type of task to be added to the task list */
    private final TaskType taskType;
    /** The input entered by the user into the command line*/
    private final String userInput;

    /**
     * Constructs an AddTaskCommand that will add the task to specified
     * task list upon execution. Supports only to-do, deadline, and event tasks.
     *
     * @param userInput The command input by the user.
     */
    public AddTaskCommand(String userInput) {
        this.userInput = userInput;
        if (userInput.matches("(?i)^(todo)(.*)")) {
            taskType = TaskType.TODO;
        } else if (userInput.matches("(?i)^(deadline)(.*)")) {
            taskType = TaskType.DEADLINE;
        } else {
            assert userInput.matches("((?i)^(event)(.*))") : "userInput should match an event regex";
            taskType = TaskType.EVENT;
        }
    }

    /**
     * Runs the command and stores the specified task (to-do, event, deadline) into the linked list,
     * provided the respective task formats are properly followed.
     *
     * @param tasks The task list the task is to be added to.
     * @param storage The storage manager that deals with loading and saving tasks to the hard disk.
     * @return Type task Response containing the added task and an added successfully message.
     * @throws DukeException If the command is not properly formatted, or lacks information.
     */
    @Override
    public Response<Task> execute(TaskList tasks, Storage storage) throws DukeException {
        // checks that the user input is correctly formatted for the task type before
        // adding it to the task list
        Task addedTask = taskType.validateCommand(userInput);
        String successMessage = tasks.addTask(addedTask);
        return new Response<>(ResponseType.TASK, successMessage, addedTask);
    }
}
