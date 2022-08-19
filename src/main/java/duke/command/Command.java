package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

import java.io.IOException;

public abstract class Command {
    public abstract boolean isTerminal();

    public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws IOException;
}
