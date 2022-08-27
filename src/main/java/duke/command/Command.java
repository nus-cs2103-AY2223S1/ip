package duke.command;

import duke.FileStorage;
import duke.task.TaskList;


/**
 * The abstract superclass of all the available commands in the chatBot Duke
 */
public abstract class Command {

    public abstract String execute(TaskList list, FileStorage storage);
}
