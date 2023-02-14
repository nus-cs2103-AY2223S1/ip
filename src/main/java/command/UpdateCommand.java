package command;

import storage.Storage;
import task.TaskList;
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String msg = "";
        switch (this.type) {
        case "mark":
            msg = ui.showMark(tasks.mark(this.num));
            break;
        case "unmark":
            msg = ui.showUnmark(tasks.unmark(this.num));
            break;
        default:
        }
        return msg;
    }
}
