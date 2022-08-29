package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class UnmarkCommand extends Command {

    private final int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }
    /**
     * Returns a String response from Duke which is the task marked as incomplete.
     * @param taskList List of tasks passed in to duke
     * @param storage Storage object to handle loading / saving from and to files
     * @return task that was marked as incomplete in String format
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String result = taskList.markTaskIncomplete(this.idx);
        try {
            storage.save(taskList);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return result;
    }
}