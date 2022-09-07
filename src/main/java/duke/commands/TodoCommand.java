package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.tasks.TodoTask;

/**
 * Command that represents the adding of a todo task.
 */
public class TodoCommand implements Command {
    private String taskName;

    /**
     * Default constructor of the Todo command.
     * @param taskName Name of the todo task.
     */
    public TodoCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Runs the todo command by adding the todo task into the tasklist and storage.
     *
     * @param tasks TaskList that contains the temporary tasks.
     * @param storage Storage that the tasks are saved at.
     * @return String output of executing the task.
     * @throws DukeException If any error occurs.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        TodoTask newTask = new TodoTask(taskName);
        tasks.add(newTask);
        storage.writeAll(tasks);
        String output = "";
        output += "Got it. I've added this task:\n";
        output += newTask + "\n";
        output += "Now you have " + tasks.getSize() + " tasks in the list\n";
        return output;
    }
    @Override
    public boolean isBye() {
        return false;
    }
}
