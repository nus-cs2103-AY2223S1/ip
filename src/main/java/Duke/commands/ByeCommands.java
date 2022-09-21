package Duke.commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import java.io.IOException;

public class ByeCommands extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        String message = ui.printBye();
        return message;
    }

    @Override
    public boolean isExit() {
        return true;
    }


}

