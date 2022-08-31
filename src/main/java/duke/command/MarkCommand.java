package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeOutOfBoundException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The MarkCommand class deletes a task from TaskList.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructor for a MarkCommand.
     *
     * @param index index of Task to be Marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task MarkedTask = taskList.mark(index);
            String message = ui.printMarkTask(MarkedTask) + '\n';
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
