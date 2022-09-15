package Duke.commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

import java.io.IOException;

public abstract class Executor extends Command {

    protected String description;

    /**
     * Class constructor.
     *
     * @param input The input provided by the user.
     */

    public Executor(String input) {
        description = input.substring(input.indexOf(' ') + 1);
    }

    /**
     * Executes the command to create a new task, printing the size of the task list after execution.
     *
     * @param tasks The task list containing all the tasks before the command is executed.
     * @param ui Provides access to the UI of the program.
     * @param storage Provides access to local storage.
     * @throws IOException when there is a problem with the IO.
     * @throws DukeException when there is a wrong input or save file issues.
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        ui.printLine(tasks.getSizeToString());
    }
}
