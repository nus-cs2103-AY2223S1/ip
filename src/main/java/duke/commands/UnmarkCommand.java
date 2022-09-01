package duke.commands;

import duke.Response;
import duke.exceptions.NoSuchTaskException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Marks a task as incomplete.
 */
public class UnmarkCommand extends Command {
    int index;

    /**
     * Constructor for UnmarkCommand.
     * @param index the index specifying the task to be deleted.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public Response execute(TaskList taskList, Storage storage) throws NoSuchTaskException {
        Task task = taskList.get(index);
        String message = String.format("OK, I've marked this task as not done yet:\n\t%s", task);
        task.markAsIncomplete();
        storage.save(taskList);
        return new Response(message);
    }
}