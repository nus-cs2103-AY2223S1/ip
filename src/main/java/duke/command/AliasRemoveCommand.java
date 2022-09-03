package duke.command;

import java.io.IOException;

import duke.internal.Parser;
import duke.internal.Storage;
import duke.internal.Ui;
import duke.task.TaskList;

/**
 * A command to remove an alias.
 * Usage: alias remove [0]
 * [0] the alias to remove
 */
public class AliasRemoveCommand extends Command {
    private final String alias;

    /**
     * Creates a new AliasRemoveCommand.
     *
     * @param alias the alias to remove
     */
    public AliasRemoveCommand(String alias) {
        this.alias = alias;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui, Parser parser) throws IOException {
        String command = parser.removeAlias(alias);
        ui.showMessage(String.format("I've removed your alias for %s!", alias))
                .showMessage(String.format("You'll have to use `%s` instead in future.", command));
    }
}
