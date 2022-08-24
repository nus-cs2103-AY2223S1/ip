package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;


/**
 * Encapsulates a user instruction to mark a saved task as completed or uncompleted.
 *
 * @author fannyjian
 */
public class UpdateCommand extends Command {
    private String type;
    private int num;

    /**
     * Initialises a command to update a saved Task.
     *
     * @param type Type of update to be performed.
     * @param num Index of task in list displayed to user.
     */
    public UpdateCommand(String type, int num) {
        this.type = type;
        this.num = num - 1;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (this.type) {
        case "mark":
            ui.showMark(tasks.mark(this.num));
            break;
        case "unmark":
            ui.showUnmark(tasks.unmark(this.num));
            break;
        }
        storage.updateStorage(tasks);
    }
}