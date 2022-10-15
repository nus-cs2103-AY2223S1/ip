package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    /**
     * Sends a message to the `ui` to say bye to the user.
     *
     * @param tasks The <code>TaskList</code> object containing all stored tasks.
     * @param ui The <code>Ui</code> object
     * @param storage The database object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sendMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Indicates whether to stop the bot.
     *
     * @return true If user stops the bot by running this command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
