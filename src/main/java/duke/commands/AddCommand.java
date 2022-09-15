package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;

public class AddCommand extends Command {

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }
    /**
     * Returns a String response from Duke which is the task most recently added.
     * @param taskList List of tasks passed in to duke
     * @param storage Storage object to handle loading / saving from and to files
     * @return task that was most recently added in String format.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String result = taskList.addTaskToList(this.task);
        try {
            storage.save(taskList);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return result;

    }
}