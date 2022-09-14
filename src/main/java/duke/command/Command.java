package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public abstract class Command {
    public Command() {

    }

    public abstract String read(TaskList tasks, Ui ui, Storage storage) throws IOException;

}
