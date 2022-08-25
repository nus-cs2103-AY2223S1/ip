package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public abstract class Command {

    /**
     * Execute some command.
     *
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    
    public abstract boolean isExit();
}
