package duke.chatbot.commandmanager.commands;

import duke.chatbot.commandmanager.commands.exceptions.InvalidArgumentsException;
import duke.chatbot.commandmanager.commands.exceptions.InvalidIndexException;
import duke.chatbot.commandmanager.commands.exceptions.NoSuchIndexException;
import duke.chatbot.taskmanager.TaskManager;

/**
 * Delete Task Command Handler that deletes a task in the list of task
 * Responds with the confirmation message stating that the task has been deleted.
 */
public class DeleteTaskCommandHandler implements Command {
    private final TaskManager taskManager;
    /**
     * Creates a new handler for the delete command with a reference to the task manager
     *
     * @param taskManager a reference to the task manager
     */
    public DeleteTaskCommandHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Deletes a task and responds with the task that was deleted.
     *
     * @param arguments string of the item number of the task to be marked
     * @return the string of the response stating the successful marking of the task
     * @throws InvalidArgumentsException thrown when an integer is not provided or out of range of the list size.
     */
    @Override
    public String execute(String arguments) throws InvalidArgumentsException {
        if (arguments.length() == 0) {
            throw new InvalidIndexException();
        }

        int itemNumber = 0;
        try {
            itemNumber = Integer.parseInt(arguments);
        } catch (NumberFormatException exception) {
            throw new InvalidIndexException();
        }
        if (itemNumber <= 0 || itemNumber > this.taskManager.getListSize()) {
            throw new NoSuchIndexException();
        }

        String deletedTask = this.taskManager.deleteTask(itemNumber);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The following item has been removed.\n");
        stringBuilder.append(deletedTask).append("\n");
        stringBuilder.append("You have ").append(this.taskManager.getListSize()).append(" item(s) remaining.\n");
        return stringBuilder.toString();
    }
}

