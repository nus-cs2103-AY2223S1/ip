package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * Abstract class for Commands.
 */
public abstract class Command {
    public abstract void execute(TaskList taskList, UI ui, Storage storage);
    public abstract boolean isExit();
}
