package duke.command;

import duke.DukeException;
import duke.Response;
import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Marks a task at a specific index in tasklist.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index > taskList.listSize()) {
            throw new DukeException("There is no " + index + " index in the list. \n");
        } else {
            taskList.markTaskAtIndex(index - 1);
            String message = "[X] You've completed a task!\n" + taskList.getTaskAtIndex(index - 1) + "\n";
            Response response = new Response(message, false, false);
            ui.setResponse(response);

        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
