package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class GreetCommand extends Command {
    /**
     * Executes the command.
     *
     * @param tasks   the list of tasks
     * @param ui      the UI
     * @param storage the storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return "Hello. I'm Jarvis.\n" + "What can I do for you?";
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
