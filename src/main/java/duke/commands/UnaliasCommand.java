package duke.commands;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class UnaliasCommand extends Command {

    private final String alias;
    private final Parser parser;

    /**
     * Constructs an UnaliasCommand with a provided alias.
     * @param alias The alias.
     * @param parser The parser for which we are adding an alias for.
     */
    public UnaliasCommand(String alias, Parser parser) {
        this.alias = alias;
        this.parser = parser;
    }

    /**
     * Creates a new alias for the provided command.
     * @param tasks The <code>TaskList</code> object containing all stored tasks.
     * @param ui The <code>Ui</code> object
     * @param storage The database object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.parser.removeAlias(this.alias);
        ui.sendMessage("I have reset the alias '" + this.alias + "'.");
    }
}
