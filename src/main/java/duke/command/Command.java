package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents commands for duke to execute actions.
 */
public interface Command {

    /**
     * The method to execute this command.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has.
     * @param ui Ui created when starting Duke.
     * @param storage Storage created when starting Duke.
     */
    void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns whether this command is an exit command.
     *
     * @return Boolean value where true indicates that this command is an exit.
     */
    boolean isExit();

}
