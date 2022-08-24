package duke.command;

import duke.task.TasksController;
import duke.Ui;
import duke.Storage;

/**
 * Abstract class Command. All commands will inherit from this class.
 */
public abstract class Command {

    /**
     * An abstract method that every child class needs to implement
     * @param controller Duke task controller
     * @param ui Duke UI
     * @param storage Duke IO processor
     */
    public abstract void execute(TasksController controller, Ui ui, Storage storage);
}
