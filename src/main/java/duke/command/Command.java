package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.DukeException;

public abstract class Command {
    public abstract String exec(TaskList taskList, Storage storage , Ui ui) throws DukeException;
}
