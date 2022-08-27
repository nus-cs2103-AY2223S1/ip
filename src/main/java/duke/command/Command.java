package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Represents commands for duke to execute actions.
 */
public interface Command {

    /**
     * The method to execute this command.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has
     * @param ui The Ui created when starting Duke
     * @param storage The Storage created when starting Duke
     */
    void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns whether this command is an exit command.
     *
     * @return boolean value where true indicates that this command is an exit.
     */
    boolean isExit();

}
