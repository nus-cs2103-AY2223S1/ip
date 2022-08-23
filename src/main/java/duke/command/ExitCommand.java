package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a command that exits the duke program.
 */
public class ExitCommand extends Command {
    /**
     * Constructs an ExitCommand.
     */
    public ExitCommand() {
        this.stillRunning = false;
    }

    /**
     * Executes the ExitCommand, displays the farewell message and saves the specified task list
     * to the hard disk with the specified Storage.
     *
     * @param tasks The task list to be saved to the hard disk
     * @param storage The storage manager that deals with loading and saving tasks to the hard disk
     * @throws DukeException If the file could not be saved to the hard disk
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        Ui.prettyPrint("Bye. Hope to see you again soon!");
        storage.writeToFile(tasks.toSaveFormat());
    }
}
