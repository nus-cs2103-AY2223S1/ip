package duke.command;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Abstract class to handle commands
 */
public abstract class Command {

    public abstract String execute(TaskList tasks, Storage storage, Ui ui);
}
