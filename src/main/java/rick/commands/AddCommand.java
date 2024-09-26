package rick.commands;

import rick.RickException;
import rick.Storage;
import rick.Ui;
import rick.tasks.Task;
import rick.tasks.TaskList;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    public Task task;

    /**
     * Constructs an AddCommand object.
     * 
     * @param instruction The instruction to be executed.
     * 
     * @param task        The task to be added.
     */
    public AddCommand(String instruction, Task task) {
        super(instruction);
        this.task = task;
    }

    /**
     * Executes the command.
     * 
     * @param tasks   The task list.
     * 
     * @param ui      The user interface.
     * 
     * @param storage The storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws RickException {
        // Detect duplicate task and prevents it from being added to the list
        if (tasks.contains(task)) {
            throw new RickException("Oops! The task cannot be added as it already exists in your list.");
        }
        tasks.add(task);
        storage.saveList(tasks);
        return "Got it. I've added this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.";

    }
}
