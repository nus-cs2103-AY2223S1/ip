package duke.command;

import duke.TaskList;
import duke.storage.Storage;

/**
 * Abstract class to handle commands
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Storage storage);
}
