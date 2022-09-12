package duke.chatbot.commandmanager.commands;

import duke.chatbot.commandmanager.commands.exceptions.InvalidCommandException;
import duke.chatbot.taskmanager.TaskManager;

/**
 * List Task Command Handler that invokes the list task method of the task manager.
 * Responds with the list of tasks returned by the task manager.
 */
public class ListTaskCommandHandler implements Command {
    /**
     * Creates a new handler for the list task command with a reference to the task manager
     *
     * @param taskManager a reference to the task manager
     */
    private final TaskManager taskManager;
    public ListTaskCommandHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Displays the list of task provided by the task manager.
     *
     * @param arguments string of arguments
     * @return the string of the list of task provided by the task manager
     * @throws InvalidCommandException thrown when there are unwanted arguments
     */
    @Override
    public String execute(String arguments) throws InvalidCommandException {
        if (arguments.length() > 0) {
            throw new InvalidCommandException();
        }

        StringBuilder stringBuilder = new StringBuilder();
        String taskListString = this.taskManager.listTask();
        if (taskListString.length() == 0) {
            stringBuilder.append("You have no tasks in your list.\n");
        } else {
            stringBuilder.append("I have your list of tasks displayed below:\n");
            stringBuilder.append(taskListString);
        }
        return stringBuilder.toString();
    }
}
