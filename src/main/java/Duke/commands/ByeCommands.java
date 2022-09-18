package Duke.commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import java.util.List;
import java.io.IOException;

public class ByeCommands extends Command {

    @Override
    public List<String> execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        return List.of(ui.printBye());
    }

    @Override
    public boolean isExit() {
        return true;
    }


}

