package duke.command;

import duke.Storage;
import duke.TaskList;

public abstract class Command {

    public abstract void execute(TaskList tasks, Storage storage);
}
