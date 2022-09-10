package drake.commands;

import drake.DrakeException;
import drake.Storage;
import drake.TaskList;
import drake.Ui;
import drake.tasks.Task;
import drake.tasks.Todo;

import java.io.IOException;

/**
 * Represents a command given by the user to create a new to-do task.
 */
public class TodoCommand extends CreateTaskCommand {

    /**
     * Constructor.
     *
     * @param fullInput The user input.
     */
    public TodoCommand(String fullInput) {
        super(fullInput);
    }

    /**
     * Executes the command to create a new to-do task, saving the new task and
     * printing the size of the task list after execution.
     *
     * @param tasks The task list before the command is executed.
     * @param ui Gives access to the UI of the program.
     * @param storage Gives access to local storage.
     * @throws IOException when there is an issue with the IO.
     * @throws DrakeException when there is inappropriate input or save file issues.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DrakeException, IOException {
        ui.printLine("I've added this task:");
        Task addedTask = tasks.addTask(new Todo(description));
        ui.printLine(addedTask);
        storage.addTask(addedTask);
        super.execute(tasks, ui, storage);
    }
}
