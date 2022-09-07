package duke.command;

import duke.ClientList;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

/**
 * Represents a command to delete a task from task list.
 */
public class DeleteTaskCommand extends Command {
    private int index;

    /**
     * Constructor for DeleteCommand class.
     *
     * @param index index of task to delete in task list.
     */
    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes task from task list and saves changes made to task list.
     *
     * @param taskList task list.
     * @param commandOutputs       user interface of program.
     * @param storage  files storing task list.
     * @return
     */
    @Override
    public String execute(TaskList taskList, CommandOutputs commandOutputs, Storage storage, ClientList clientList) throws DukeException {
        Task deletedTask;
        try {
            deletedTask = taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("Index %s does not exist on the list.", index + 1));
        }
        taskList.delete(index);
        new SaveCommand().execute(taskList, commandOutputs, storage, clientList);
        return commandOutputs.showDelete(taskList, deletedTask);
    }
}
