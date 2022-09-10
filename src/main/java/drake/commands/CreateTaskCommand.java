package drake.commands;

import drake.DrakeException;
import drake.Storage;
import drake.TaskList;
import drake.Ui;

import java.io.IOException;

/**
 * Represents a command given by the user to create a new task.
 */
public abstract class CreateTaskCommand extends Command {

    protected String description;

    /**
     * Constructor.
     *
     * @param fullInput The input given by the user.
     */
    public CreateTaskCommand(String fullInput) {
        description = fullInput.substring(fullInput.indexOf(' ') + 1);
    }

    /**
     * Executes the command to create a new task, printing the size of the task list after execution.
     *
     * @param tasks The task list before the command is executed.
     * @param ui Gives access to the UI of the program.
     * @param storage Gives access to local storage.
     * @throws IOException when there is an issue with the IO.
     * @throws DrakeException when there is inappropriate input or save file issues.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DrakeException {
        ui.printLine(tasks.getSizeToString());
    }
}
