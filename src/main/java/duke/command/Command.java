package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * The abstract class for all the commands in the chatbot.
 */
public abstract class Command {

    public abstract String execute(TaskList tasks, Storage storage);

}
