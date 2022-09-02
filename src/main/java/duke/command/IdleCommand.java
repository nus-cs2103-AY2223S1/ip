package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class IdleCommand extends Command {
    /**
     * Executes the command.
     *
     * @param tasks   the list of tasks
     * @param ui      the UI
     * @param storage the storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return "I'm sorry, but I don't quite understand what that means.";
    }

    /**
     * Returns a boolean value represent whether to exit the programme
     * after the command is executed.
     *
     * @return a boolean value represent whether to exit the programme
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
