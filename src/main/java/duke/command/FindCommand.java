package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that finds tasks in the list matching the input String.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class FindCommand extends Command {

    private String target;

    public FindCommand(String target) {
        this.target = target;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showFound();
        taskList.find(target);
    }
}
