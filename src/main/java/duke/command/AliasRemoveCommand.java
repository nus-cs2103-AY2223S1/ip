package duke.command;

import java.io.IOException;

import duke.internal.MessageBuilder;
import duke.internal.Parser;
import duke.internal.Storage;
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
    public void execute(TaskList tasks, Storage storage, MessageBuilder messageBuilder,
                        Parser parser) throws IOException {
        String command = parser.removeAlias(alias);
        messageBuilder.addLine(String.format("I've removed your alias for %s!", alias))
                .addLine(String.format("You'll have to use `%s` instead in future.", command));
    }
}
