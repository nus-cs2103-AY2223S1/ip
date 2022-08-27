package kirby.commands;

import kirby.TaskList;
import kirby.Ui;
import kirby.Storage;
import kirby.exceptions.KirbyMissingArgumentException;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException {
        System.out.println("I loved talking to you ･ω･\n" + "Hope to see you again!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
