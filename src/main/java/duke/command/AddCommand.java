package duke.command;

import duke.TaskList;
import duke.task.Task;

/**
 * Represents a AddCommand to add ToDo/ Deadline/ Event task. This class implements Command Interface.
 */
public class AddCommand implements Command {

    protected TaskList tasks;
    protected String description;

    /**
     * This method is a AddCommand constructor.
     * A AddCommand consists of a TaskList, a command description string containing add command parameters.
     * @param taskList a list of tasks.
     * @param description command description string containing add command parameters.
     */
    public AddCommand(TaskList taskList, String description) {
        this.tasks = taskList;
        this.description = description;
    }

    /**
     * Executes add command to adda task to the TaskList.
     */
    public void execute(Task t) {
        tasks.addTask(t);
    }
}
