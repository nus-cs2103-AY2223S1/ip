package duke.command;

import duke.data.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * ListCommand class 
 */
public class ListCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.printList();
    }
}
