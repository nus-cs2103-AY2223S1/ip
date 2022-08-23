package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.Ui;
import duke.task.TaskList;

public class InvalidCommand extends Command {
    public static final String COMMAND_ID = "";

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
