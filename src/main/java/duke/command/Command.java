package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * An abstract command class, other commands inherit the methods execute and isExit for different implementations
 */
public abstract class Command {

    /**
     * Abstract method with different implementations for other commands
     *
     * @param taskList tasklist that contains tasks to be modified, added, or removed
     * @param ui ui that displays results of user commands
     * @param storage storage that saves or loads the taskList
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Abstract method that determines if the commands will end the program.
     *
     * @return true if the command ends the program and vice versa
     */
    public abstract boolean isExit();
}
