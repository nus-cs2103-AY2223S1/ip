package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * A class that encapsulates the exit command.
 */
public class ExitCommand extends Command {

    /**
     * Handles the execution behaviour of the exit command.
     *
     * @param tasks The current list of tasks.
     * @param ui The UI of the Duke bot.
     * @param storage The storage of data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.byeMessage();
        ui.printDivider();
    }

    /**
     * Returns the command type.
     *
     * @return The command type.
     */
    @Override
    public String getCommand() {
        return "exit";
    }

    /**
     * Returns the string representation of the command.
     *
     * @return The string representation of the command.
     */
    @Override
    public String toString() {
        return "exit";
    }
}
