/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command{
    private int index;

    public MarkCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMark(tasks.get(index));
        storage.savetoFile(tasks.saveList());
    }
}

