package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;

/**
 * Represents a command that adds a specific task type to a specified task list based on
 * the user's input upon its execution.
 */
public class AddTaskCommand extends Command {
    private TaskType taskType;
    private String userInput;

    /**
     * Constructs an AddTaskCommand that will add the task to specified
     * task list upon execution. Supports only to-do, deadline, and event tasks.
     *
     * @param userInput The command input by the user
     */
    public AddTaskCommand(String userInput) {
        this.userInput = userInput;
        if (userInput.matches("(?i)^(todo)(.*)")) {
            this.taskType = TaskType.TODO;
        } else if (userInput.matches("(?i)^(deadline)(.*)")) {
            this.taskType = TaskType.DEADLINE;
        } else if (userInput.matches("(?i)^(event)(.*)")) {
            this.taskType = TaskType.EVENT;
        }
    }

    /**
     * Runs the command and stores the specified task (to-do, event, deadline) into the linked list,
     * provided the respective task formats are properly followed.
     *
     * @param tasks The task list the task is to be added to
     * @param storage The storage manager that deals with loading and saving tasks to the hard disk
     * @throws DukeException If the command is not properly formatted, or lacks information
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task addedTask = this.taskType.validateCommand(this.userInput);
        return tasks.addTask(addedTask);
    }
}
