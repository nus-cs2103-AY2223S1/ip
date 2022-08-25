package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Abstract class to handle commands
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Storage storage);
}
