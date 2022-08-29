package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class DeleteCommand extends Command {

    private final int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }
    /**
     * Returns a String response from Duke.
     * @param taskList List of tasks passed in to duke
     * @param storage Storage object to handle loading / saving from and to files
     * @return response from Duke in String format.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String result = taskList.deleteTask(this.idx);
        try {
            storage.save(taskList);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return result;
    }
}