package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to save task list in save file.
 */
public class SaveCommand extends Command {

    /**
     * Constructor for SaveCommand class.
     */
    public SaveCommand() {
        super(false);
    }

    /**
     * Saves tasks in task list to save file.
     *
     * @param taskList task list.
     * @param ui user interface of program.
     * @param storage files storing task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.saveTaskList(taskList);
    }
}
