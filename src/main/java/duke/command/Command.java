package duke.command;

import duke.CommandHistory;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * An abstract class which represents the Command given by the user.
 */
public abstract class Command {

    public abstract String execute(Ui ui, Storage storage, TaskList taskList,
            CommandHistory commandHistory);

    public abstract String undoExecute(Ui ui, Storage storage, TaskList taskList,
            CommandHistory commandHistory);
}
