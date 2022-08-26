package duke.command;

import duke.storage.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Abstract class to handle commands
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Storage storage, Ui ui);
}
