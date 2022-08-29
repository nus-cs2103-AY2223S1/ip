package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    /**
     * Tells the main class whether to terminate or not.
     * @return
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Execute and carry out the given command.
     * @param tasks Contains the task list.
     * @param ui Responsible for interacting with the user.
     * @param storage Responsible for loading/saving tasks from/in the file.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
