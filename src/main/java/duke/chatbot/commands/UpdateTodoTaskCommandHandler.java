package duke.chatbot.commands;

import duke.chatbot.commands.exceptions.EmptyTaskException;
import duke.chatbot.commands.exceptions.InvalidArgumentsException;
import duke.taskmanager.TaskManager;

/**
 * Update To Do Task Command handler that handles the updating of to do tasks in the
 * list of task managed by the task manager.
 */
public class UpdateTodoTaskCommandHandler implements UpdateCommand {
    private TaskManager taskManager;
    /**
     * Creates a new handler for the update task command with a reference to the task manager.
     *
     * @param taskManager a reference to the task manager
     */
    public UpdateTodoTaskCommandHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Updates the task name of the to do task indicated by the item number.
     *
     * @param itemNumber the item number of the task to be updated
     * @param updatedTodoTaskName the updated task name
     * @return the string of the updated task
     * @throws InvalidArgumentsException
     */
    @Override
    public String execute(int itemNumber, String updatedTodoTaskName) throws InvalidArgumentsException {
        if (updatedTodoTaskName.length() == 0) {
            throw new EmptyTaskException();
        }
        return this.taskManager.updateTask(itemNumber, updatedTodoTaskName);
    }
}
