package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

abstract public class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();

}
