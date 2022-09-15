package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * Represents a command that requires to add a deadline or a todo or a event.
 *
 */
public class AddCommand extends Command {
    private String fullCommand;
    /**
     * Class constructor.
     * @param fullCommand A string which is the same as a line of user input.
     *                    Has legal start as listed in CommandType, followed by different format of
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
    public String execute(TaskList taskList, Storage storage, CommandType c) throws DukeException {
        Task newTask = Task.createATask(fullCommand, c);
        String res = taskList.addTask(newTask);
        storage.updateFile(taskList.getTaskList());
        return res;
    }
}
