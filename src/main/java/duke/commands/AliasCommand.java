package duke.commands;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class AliasCommand extends Command {

    private final String command;
    private final String alias;
    private final Parser parser;

    /**
     * Constructs an AliasCommand with a provided command and its alias.
     *
     * The alias command cannot be overwritten, and the alias cannot contain the '=' character.
     * @param alias The alias.
     * @param command The command to be aliased.
     * @param parser The parser for which we are adding an alias for.
     * @throws DukeException If user tries to overwrite the alias command, or if the alias contains the '=' character.
     */
    public AliasCommand(String alias, String command, Parser parser) throws DukeException {
        if (command.equals("alias")) {
            throw new DukeException("The alias command cannot be overwritten.");
        }

        if (command.equals("unalias")) {
            throw new DukeException("The unalias command cannot be overwritten.");
        }

        if (alias.indexOf('=') != -1) {
            throw new DukeException("Alias cannot contain the '=' character.");
        }

        this.command = command;
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
        this.parser.addAlias(this.alias, this.command);
        ui.sendMessage("Successfully added '" + this.alias + "' as an alias for the command '" + this.command + "'.");
    }
}
