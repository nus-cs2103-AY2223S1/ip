package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ListCommand prints out all tasks in tasks list.
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand.
     */
    public ListCommand() {
        super();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showListMessage(taskList);
    }
}
