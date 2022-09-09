package duke.command;

import java.io.IOException;

import duke.internal.Parser;
import duke.internal.Storage;
import duke.internal.Ui;
import duke.task.TaskList;

/**
 * A command to list all aliases.
 * Usage: alias list
 */
public class AliasListCommand extends Command {
    private final String aliases;

    public AliasListCommand(String aliases) {
        this.aliases = aliases;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui, Parser parser) throws IOException {
        ui.showMessage("Here are your aliases!").showMessage(aliases);
    }
}
