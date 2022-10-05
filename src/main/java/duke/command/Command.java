package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.dukeexception.UnknownCommandException;

public abstract class Command {
    public Command() {}

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws UnknownCommandException;
}
