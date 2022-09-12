package duke.chatbot.commands;

import duke.chatbot.commands.exceptions.EmptyTaskException;
import duke.taskmanager.TaskManager;
import duke.taskmanager.task.ToDoTask;

/**
 * To Do Task Command Handler that adds a new To Do task to the list of task
 * managed by the task manager.
 * Responds with the confirmation message stating that a new task is added.
 */
public class TodoTaskCommandHandler implements CommandHandler {
    private final TaskManager taskManager;
    /**
     * Creates a new handler for the to do task command with a reference to the task manager
     *
     * @param taskManager a reference to the task manager
     */
    public TodoTaskCommandHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

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
