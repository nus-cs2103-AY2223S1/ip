package duke.commands;

import java.util.function.Supplier;

import duke.exceptions.DukeException;
import duke.managers.TaskManager;
import duke.models.task.Task;

/**
 * Encapsulates a base command for adding a {@link Task} that provides a utility method to interact with the task
 * manager.
 * <p>
 * Subclasses of the class can call the {@link #addTask(TaskManager, Supplier)} method in the class to delegate the
 * task manager to add the corresponding task.
 * </p>
 *
 * @author Emily Ong Hui Qi
 */
abstract class AddTaskCommand implements Command {
    private static final String MESSAGE_ADD_TASK = "Got it. I've added this task:";

    private static final String ASSERTION_TASK_IS_NON_NULL = "A task must be non-null to be added.";

    /**
     * Retrieves the task supplied by the caller and adds that task to the specified task manager.
     *
     * @param taskManager  Task manager in charge of the managing all the tasks.
     * @param taskSupplier Supplier method that returns a task.
     *
     * @return Status message indicating the status of adding the task.
     */
    public String addTask(TaskManager taskManager, Supplier<? extends Task> taskSupplier) {
        Task task = taskSupplier.get();
        assert task != null : AddTaskCommand.ASSERTION_TASK_IS_NON_NULL;
        try {
            task = taskManager.add(task);
            return String.format(
                    "%s\n\t%s\n%s",
                    AddTaskCommand.MESSAGE_ADD_TASK,
                    task,
                    taskManager.getStatus()
            );
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
