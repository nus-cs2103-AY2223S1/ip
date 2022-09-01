package duke.commands;

import duke.Response;
import duke.exceptions.NoSuchTaskException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Deletes a task from the task list.
 */
public class DeleteTaskCommand extends Command {
    int index;

    /**
     * Constructor for DeleteTaskCommand.
     * @param index the index specifying the task to be deleted.
     */
    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public Response execute(TaskList taskList, Storage storage) throws NoSuchTaskException {
        Task task = taskList.delete(index);
        String message = String.format("Noted. I've removed this task:\n\t%s\n", task);
        storage.save(taskList);
        return new Response(message);
    }
}