package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

import java.io.IOException;

public class ExitCommand extends Command{
    public ExitCommand() {}

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        ui.exit();
        return "Bye. Hope to see you again soon!";
    }
}
