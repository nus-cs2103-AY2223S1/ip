package handlers;

import models.Task;
import models.TaskManager;

import java.util.function.Supplier;

abstract public class AddTaskCommand implements DukeCommand{
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task:";

    /**
     * Retrieve the task supplied by the caller and adds that task to the specified task manager.
     *
     * @param taskManager Task manager in charge of the list of tasks
     * @param taskSupplier Supplier function that returns a task
     * @return Status message
     */
    public String addTask(TaskManager taskManager, Supplier<? extends Task> taskSupplier) {
        Task task = taskSupplier.get();
        taskManager.add(task);
        return String.format(
                "%s\n\t%s\n%s",
                AddTaskCommand.ADD_TASK_MESSAGE,
                task,
                taskManager.getStatus()
        );
    }
}
