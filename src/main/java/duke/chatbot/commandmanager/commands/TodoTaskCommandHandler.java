package duke.chatbot.commandmanager.commands;

import duke.chatbot.commandmanager.commands.exceptions.EmptyTaskException;
import duke.chatbot.taskmanager.TaskManager;
import duke.chatbot.taskmanager.task.ToDoTask;

/**
 * To Do Task Command Handler that adds a new To Do task to the list of task
 * managed by the task manager.
 * Responds with the confirmation message stating that a new task is added.
 */
public class TodoTaskCommandHandler implements Command {
    private final TaskManager taskManager;
    /**
     * Creates a new handler for the to do task command with a reference to the task manager
     *
     * @param taskManager a reference to the task manager
     */
    public TodoTaskCommandHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Adds a new to do task to the task manager with the given attributes and responds with a confirmation message.
     *
     * @param arguments string of the task name
     * @return the string of the response stating the successful addition of the new to do task
     * @throws EmptyTaskException thrown when the task name is empty
     */
    @Override
    public String execute(String arguments) throws EmptyTaskException {
        if (arguments.length() == 0) {
            throw new EmptyTaskException();
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("> Added: ");
        stringBuilder.append(taskManager.addTask(new ToDoTask(arguments)));
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
