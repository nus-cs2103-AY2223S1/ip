package duke.chatbot.commandmanager.commands;

import duke.chatbot.commandmanager.commands.exceptions.InvalidArgumentsException;
import duke.chatbot.commandmanager.commands.exceptions.InvalidIndexException;
import duke.chatbot.commandmanager.commands.exceptions.NoSuchIndexException;
import duke.chatbot.personality.Personality;
import duke.chatbot.taskmanager.TaskManager;

/**
 * Unmark Task Command Handler that marks a task in the list of task as not completed.
 * Responds with the confirmation message stating that the task has been successfully unmarked.
 */
public class UnmarkTaskCommandHandler implements Command {
    private final Personality personality;
    private final TaskManager taskManager;
    /**
     * Creates a new handler for the unmark task command with a reference to the task manager
     * and the chatbot's personality.
     *
     * @param personality a reference to the chatbot's personality
     * @param taskManager a reference to the task manager
     */
    public UnmarkTaskCommandHandler(Personality personality, TaskManager taskManager) {
        this.personality = personality;
        this.taskManager = taskManager;
    }

    /**
     * Marks a task as not completed and responds with a confirmation message.
     *
     * @param arguments string of the item number of the task to be unmarked
     * @return the string of the response stating the successful unmarking of the task
     * @throws InvalidArgumentsException thrown when an integer is not provided or out of range of the list size.
     */
    @Override
    public String execute(String arguments) throws InvalidArgumentsException {
        if (arguments.length() == 0) {
            throw new InvalidIndexException(this.personality);
        }

        int itemNumber = 0;
        try {
            itemNumber = Integer.parseInt(arguments);
        } catch (NumberFormatException exception) {
            throw new InvalidIndexException(this.personality);
        }
        if (itemNumber <= 0 || itemNumber > this.taskManager.getListSize()) {
            throw new NoSuchIndexException(this.personality);
        }

        boolean isUnmarkedSuccessfully = this.taskManager.unmarkTask(itemNumber);
        if (isUnmarkedSuccessfully) {
            return personality.formulateResponse("unmark_task_success");
        } else {
            return personality.formulateResponse("unmark_task_fail");
        }
    }
}

