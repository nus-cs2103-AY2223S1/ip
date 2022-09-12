package duke.chatbot.commands;

import duke.chatbot.commands.exceptions.InvalidCommandException;
import duke.taskmanager.TaskManager;

/**
 * List Task Command Handler that invokes the list task method of the task manager.
 * Responds with the list of tasks returned by the task manager.
 */
public class ListTaskCommandHandler implements CommandHandler {
    /**
     * Creates a new handler for the list task command with a reference to the task manager
     *
     * @param taskManager a reference to the task manager
     */
    private final TaskManager taskManager;
    public ListTaskCommandHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public String execute(String arguments) throws InvalidCommandException {
        if (arguments.length() > 0) {
            throw new InvalidCommandException();
        }

        StringBuilder stringBuilder = new StringBuilder();
        String taskListString = taskManager.listTask();
        if (taskListString.length() == 0) {
            stringBuilder.append("You have no tasks in your list.\n");
        } else {
            stringBuilder.append("I have your list of tasks displayed below:\n");
            stringBuilder.append(taskListString);
        }
        return stringBuilder.toString();
    }
}
