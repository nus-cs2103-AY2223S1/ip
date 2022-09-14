package duke.chatbot.commandmanager.commands;

import duke.chatbot.commandmanager.commands.exceptions.InvalidCommandException;
import duke.chatbot.personality.Personality;
import duke.chatbot.taskmanager.TaskManager;

/**
 * Find Task Command Handler that finds all tasks in the list of tasks containing the given keyword.
 * Responds with the list of tasks that contains the given keyword.
 */
public class FindTaskCommandHandler implements Command {
    private final Personality personality;
    private final TaskManager taskManager;
    /**
     * Creates a new handler for the find task command with a reference to the task manager
     * and the chatbot's personality
     *
     * @param personality a reference to the chatbot's personality
     * @param taskManager a reference to the task manager
     */
    public FindTaskCommandHandler(Personality personality, TaskManager taskManager) {
        this.personality = personality;
        this.taskManager = taskManager;
    }

    /**
     * Finds all tasks that contain the given keyword and responds with a list containing such tasks.
     *
     * @param arguments the keyword to search for in all tasks
     * @return the string of the response displaying the list of matching tasks
     * @throws InvalidCommandException thrown when the keyword is empty
     */
    @Override
    public String execute(String arguments) throws InvalidCommandException {
        if (arguments.length() == 0) {
            throw new InvalidCommandException();
        }

        String matchedTaskList = this.taskManager.findTask(arguments);
        if (matchedTaskList.length() == 0) {
            return personality.formulateResponse("find_task_empty", arguments);
        }
        return personality.formulateResponse("find_task", matchedTaskList);
    }
}

