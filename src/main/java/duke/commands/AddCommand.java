package duke.commands;

import duke.Storage;
import duke.DukeException;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

/*
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    public Task task;

    /*
     * Constructs an AddCommand object.
     * 
     * @param instruction The instruction to be executed.
     * 
     * @param task The task to be added.
     */
    public AddCommand(String instruction, Task task) {
        super(instruction);
        this.task = task;
    }

    /*
     * Executes the command.
     * 
     * @param tasks The task list.
     * 
     * @param ui The user interface.
     * 
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task);
        ui.print(ui.addLineBreak(
                "Got it. I've added this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list."));
        storage.saveList(tasks);
    }
}
