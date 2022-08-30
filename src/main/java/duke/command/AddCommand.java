package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * Represents a command. An AddCommand Object corresponds to a command
 */

public class AddCommand extends Command {
    private String fullCommand;
    /**
     * An object constructor. Gives an AddCommand object.
     * @param fullCommand A string which is the same as a line of System.in. Start with
     *                    'event' or 'deadline' or 'todo', followed by different format of
     *                    description of the task.
     */
    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    /**
     * Execute the given command. Add a task to the taskList.
     * Update the tasks stored in the file.Throws exception when execution fails.
     * @param storage A Storage object containing the operation of the file.
     *                Update its file content in this method.
     * @param taskList A TaskList object containing the target taskList.
     *                 Add a new task to taskList in this method
     * @throws DukeException Throws DukeException when the file cannot be updated
     *                       or the task cannot be created.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.addTask(Task.createATask(fullCommand));
        storage.updateFile(taskList.getTaskList());
    }
}
