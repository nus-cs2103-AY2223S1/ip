package duke.command;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Abstract class that contains method that should be included in all children classes,
 * with the aim of running a command.
 */
public abstract class Command {

    /**
     * Abstract method that runs the command internally, handled by all Classes that inherits Command.
     * @param ui the ui class that prints text in a readable format
     * @param storage the storage object which handles reading and writing of data
     * @param taskList the list of tasks currently stored
     * @return
     */
    public abstract String runCommand(Ui ui, Storage storage, TaskList taskList);
}
