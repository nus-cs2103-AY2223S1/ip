package duke.commands;

import duke.Storage;

public abstract class Command {

    protected String description;

    public Command(String description) {
        this.description = description;
    }

    public abstract void execute(Storage storage);
}
