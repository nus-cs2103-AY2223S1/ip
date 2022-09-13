package duke.command;

import duke.ClientList;
import duke.DukeException;
import duke.task.TaskList;
import duke.task.Task;

/**
 * Represents a command to delete a task from task list.
 */
public class DeleteTaskCommand extends Command {
    private final int index;

    /**
     * Constructs a Delete Command object.
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
     * @param clientList client list.
     * @return String representation of deleted task.
     * @throws DukeException if index does is out of bounds in the task list.
     */
    @Override
    public String execute(TaskList taskList, ClientList clientList) throws DukeException {
        Task deletedTask;
        try {
            deletedTask = taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("Index %s does not exist on the list.", index + 1));
        }
        taskList.delete(index);
        SaveTaskListCommand.of().execute(taskList, clientList);
        return CommandOutputs.showDelete(taskList, deletedTask);
    }
}
