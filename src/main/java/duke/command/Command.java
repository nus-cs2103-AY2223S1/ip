package duke.command;

import duke.DukeException;
import duke.oop.Storage;
import duke.oop.TaskList;
import duke.oop.Ui;

public abstract class Command {
    public boolean isExit;

    public Command() {}

    public abstract String execute(TaskList taskList, Storage storage, Ui ui) throws DukeException;

}
