package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Exits the bot when command is called.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }

    /**
     * Exits the bot.
     *
     * @return true to exit the bot.
     */
    @Override
    public boolean exitBot() {
        return true;
    }
}
