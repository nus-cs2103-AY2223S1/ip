package duke.commands;

import duke.Storage;

/**
 * Represents an executable <code>Command</code>.
 */
public abstract class Command {

    protected String description;

    /**
     * Constructs a <code>Command</code> object.
     *
     * @param description
     */
    public Command(String description) {
        this.description = description;
    }

    /**
     * Executes the command
     *
     * @param storage Storage object that communicate with local storage.
     */
    public abstract String execute(Storage storage);
}
