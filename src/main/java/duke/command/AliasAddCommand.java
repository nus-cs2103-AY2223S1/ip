package duke.command;

import java.io.IOException;

import duke.internal.Parser;
import duke.internal.Storage;
import duke.internal.Ui;
import duke.task.TaskList;

/**
 * A command to add an alias.
 * Usage: alias add [0] [1]
 * [0] the alias to add
 * [1] the command to add the alias to
 */
public class AliasAddCommand extends Command {
    private final String alias;
    private final String command;

    /**
     * Creates a new AliasAddCommand.
     *
     * @param alias the alias to add
     * @param command the command to add the alias to
     */
    public AliasAddCommand(String alias, String command) {
        this.alias = alias;
        this.command = command;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui, Parser parser) throws IOException {
        parser.addAlias(alias, command);
        ui.showMessage("I've added your alias for " + command + "!")
                .showMessage(String.format("You can now use `%s` in place of `%s`.", alias, command));
    }
}
