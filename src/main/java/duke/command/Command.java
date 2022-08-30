package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;


/**
 * Represents an abstract command type
 */
public abstract class Command {

    /**
     * Execute will add a task into the tasklist, save the new tasklist in storage
     * and print out what was added
     *
     * @param task
     * @param ui
     * @param storage
     */
    public abstract void execute(TaskList task, Ui ui, Storage storage);
}
