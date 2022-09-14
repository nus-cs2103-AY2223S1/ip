package duke.chatbot.commandmanager.commands;

import duke.chatbot.commandmanager.commands.exceptions.EmptyTaskException;
import duke.chatbot.commandmanager.commands.exceptions.InvalidArgumentsException;
import duke.chatbot.personality.Personality;
import duke.chatbot.taskmanager.TaskManager;

/**
 * Update To Do Task Command handler that handles the updating of to do tasks in the
 * list of task managed by the task manager.
 */
public class UpdateTodoTaskCommandHandler implements UpdateCommand {
    private final Personality personality;
    private final TaskManager taskManager;
    /**
     * Creates a new handler for the update task command with a reference to the task manager
     * and the chatbot's personality
     *
     * @param taskManager a reference to the chatbot's personality
     * @param taskManager a reference to the task manager
     */
    public UpdateTodoTaskCommandHandler(Personality personality, TaskManager taskManager) {
        this.personality = personality;
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
            throw new EmptyTaskException(this.personality);
        }
        return this.taskManager.updateTask(itemNumber, updatedTodoTaskName);
    }
}
