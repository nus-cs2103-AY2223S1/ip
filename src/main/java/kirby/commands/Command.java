package kirby.commands;

import kirby.TaskList;
import kirby.Ui;
import kirby.Storage;
import kirby.exceptions.KirbyMissingArgumentException;

public abstract class Command {
    public Command(String inputString) {
        String command = inputString.split(" ")[0];
    }

    public Command() {
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException;

public abstract boolean isExit();
}
