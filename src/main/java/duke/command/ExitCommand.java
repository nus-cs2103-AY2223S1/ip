package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Class to encapsulate a command that exits the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructor for ExitCommand.
     */
    public ExitCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Return if command should cause exit from program.
     *
     * @return Whether the command causes exit from program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
