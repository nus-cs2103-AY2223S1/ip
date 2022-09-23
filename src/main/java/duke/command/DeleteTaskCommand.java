package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteTaskCommand extends Command {
    private final int index;

    /**
     * Initialises a {@code DeleteTaskCommand} with an {@code int} index.
     *
     * @param index The index of the {@code Task} to be deleted.
     */
    public DeleteTaskCommand(int index) {
        assert index >= 0;
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        try {
            Task task = tasks.remove(index);
            try {
                Storage.write(tasks);
            } catch (DukeException e) {
                e.printStackTrace();
            }
            return String.format("Noted. I've removed this task:\n %s",
                task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid argument: Index of task should be between 1 and the number of tasks.");
        }
    }
}
