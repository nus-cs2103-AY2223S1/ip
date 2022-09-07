package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents a todo command
 */
public class TodoCommand extends Command {
    private String taskDescription;

    /**
     * Creates an task command.
     *
     * @param taskDescription description of task being created
     */
    public TodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Executes the command.
     *
     * @param taskList list of stored tasks
     * @return successful task addition message
     * @throws DukeException if date/time format is incorrect
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        Todo newTask = new Todo(taskDescription);
        return taskList.addTask(newTask);
    }
}
