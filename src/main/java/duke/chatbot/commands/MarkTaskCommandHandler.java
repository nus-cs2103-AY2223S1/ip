package duke.chatbot.commands;

import duke.chatbot.commands.exceptions.InvalidArgumentsException;
import duke.chatbot.commands.exceptions.InvalidIndexException;
import duke.chatbot.commands.exceptions.NoSuchIndexException;
import duke.taskmanager.TaskManager;

/**
 * Mark Task Command Handler that marks a task in the list of task as completed.
 * Responds with the confirmation message stating that the task has been successfully marked.
 */
public class MarkTaskCommandHandler implements Command {
    private final TaskManager taskManager;
    /**
     * Creates a new handler for the mark task command with a reference to the task manager
     *
     * @param taskManager a reference to the task manager
     */
    public MarkTaskCommandHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Marks a task as completed and responds with a confirmation message.
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

        boolean isMarkedSuccessfully = this.taskManager.markTask(itemNumber);
        if (isMarkedSuccessfully) {
            return "I've marked this task as done. Good Job!\n";
        } else {
            return "The task is already marked you dummy.\n";
        }
    }
}

