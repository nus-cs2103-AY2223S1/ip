package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

public class ListCommand extends Command {
    private static final ListCommand LIST_COMMAND = new ListCommand();

    private ListCommand() {
        super(false);
    }

    public static ListCommand of() {
        return LIST_COMMAND;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        ui.showListOut(taskList);
    }
}
