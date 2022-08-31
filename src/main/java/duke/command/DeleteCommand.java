package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeOutOfBoundException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The DeleteCommand class deletes a task from TaskList.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for a DeleteCommand.
     *
     * @param index index of Task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task deletedTask = taskList.delete(index);
            String message = ui.printDeleteTask(deletedTask) + '\n';
            message += ui.printSizeOfList(taskList.size());
            storage.save(taskList);
            return message;
        } catch (DukeOutOfBoundException e) {
            return ui.printErr(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
