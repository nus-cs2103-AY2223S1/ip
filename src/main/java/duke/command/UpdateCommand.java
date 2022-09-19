package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * UpdateCommand is the command for when the user wants to update a task.
 */
public class UpdateCommand extends Command {
    private int num;
    private String update;

    /**
     * Constructor for UpdateCommand.
     *
     * @param num Number of the task to be updated.
     */
    public UpdateCommand(int num, String update) {
        this.num = num;
        this.update = update;
    }

    @Override
    public String execCommand(TaskList list, Ui ui, Storage storage) {
        list.getTask(this.num).update(this.update);
        storage.saveList(list.save());

        return ui.showUpdate(list.getTask(this.num));
    }
}
