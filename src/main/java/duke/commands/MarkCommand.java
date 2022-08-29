package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class MarkCommand extends Command {

    private final int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }
    /**
     * Returns a String response from Duke which is the task marked as complete.
     * @param taskList List of tasks passed in to duke
     * @param storage Storage object to handle loading / saving from and to files
     * @return task that was marked as complete in String format
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String result = taskList.markTaskComplete(this.idx);
        try {
            storage.save(taskList);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return result;
    }
}