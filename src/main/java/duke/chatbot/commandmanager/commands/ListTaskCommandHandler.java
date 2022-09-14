package duke.chatbot.commandmanager.commands;

import duke.chatbot.commandmanager.commands.exceptions.InvalidCommandException;
import duke.chatbot.personality.Personality;
import duke.chatbot.taskmanager.TaskManager;

/**
 * List Task Command Handler that invokes the list task method of the task manager.
 * Responds with the list of tasks returned by the task manager.
 */
public class ListTaskCommandHandler implements Command {
    private final Personality personality;
    private final TaskManager taskManager;

    /**
     * Creates a new handler for the list task command with a reference to the task manager
     * and the chatbot's personality
     *
     * @param personality a reference to the chatbot's personality
     * @param taskManager a reference to the task manager
     */
    public ListTaskCommandHandler(Personality personality, TaskManager taskManager) {
        this.personality = personality;
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
            throw new InvalidCommandException(this.personality);
        }

        String taskListString = this.taskManager.listTask();
        if (taskListString.length() == 0) {
            return personality.formulateResponse("list_task_empty");
        } else {
            return personality.formulateResponse("list_task", taskListString);
        }
    }
}
