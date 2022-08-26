package duke.command;

import java.io.IOException;
import duke.Ui;
import duke.Storage;
import duke.TaskList;
import duke.DukeException;

public abstract class Command {

    public abstract boolean isExit();

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;

}
