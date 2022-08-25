package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command{
    /**
     * Constructor for ExitCommand.
     */
    public ExitCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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
