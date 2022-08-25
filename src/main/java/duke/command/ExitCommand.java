package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructor for ExitCommand.
     *
     * @param cmd Type of command
     */
    public ExitCommand(String cmd) {
        super(cmd);
    }

    /**
     * Execute the exit command and prompt the program to terminate
     *
     * @param ui Ui to show Exit operation messages
     * @param taskList TaskList to execute command
     * @throws DukeException If invalid commands or arguments
     */
    @Override
    public void execute(Ui ui, TaskList taskList) throws DukeException {
        ui.showExitMsg();
    }
}
