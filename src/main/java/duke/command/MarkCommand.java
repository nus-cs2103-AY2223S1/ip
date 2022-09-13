package duke.command;

import duke.ClientList;
import duke.DukeException;
import duke.task.TaskList;

/**
 * Represents a command to mark a task in task list.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a Mark Command object.
     *
     * @param index index of task in task list.
     */
    public MarkCommand(int index) {
        assert index >= 0;
        this.index = index;
    }

    /**
     * Marks task in task list and saves it.
     *
     * @param taskList task list.
     * @param clientList client list.
     * @return String representation of task being marked.
     * @throws DukeException if task is already marked or index does not exist.
     */
    @Override
    public String execute(TaskList taskList, ClientList clientList) throws DukeException {
        try {
            if (taskList.get(index).isDone()) { //Guard Clause
                throw new DukeException("Task is already marked");
            }
            taskList.get(index).markTask();
            SaveTaskListCommand.of().execute(taskList, clientList);
            return CommandOutputs.showMark(taskList, index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(
                    String.format("Index %d does not exist on the list.", index + 1)); //plus 1 for indexing
        }
    }
}
