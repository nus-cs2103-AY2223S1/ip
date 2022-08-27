package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that exits the Duke program.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        storage.save(taskList.getTaskArrayList());
        ui.showGoodbye();
    }
}
