package duke.commands;

import duke.Response;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Creates and stores a task.
 */
class CreateTaskCommand extends Command {
    Task task;

    /**
     * Constructor for CreateTaskCommand.
     * @param task the task to be stored
     */
    CreateTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public Response execute(TaskList taskList, Storage storage) {
        taskList.store(task);

        String message = String.format("Got it. I've added this task:\n\t%s\n", task);

        storage.save(taskList);

        return new Response(message);
    }
}