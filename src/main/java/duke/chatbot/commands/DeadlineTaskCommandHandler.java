package duke.chatbot.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.chatbot.commands.exceptions.EmptyTaskException;
import duke.chatbot.commands.exceptions.InvalidArgumentsException;
import duke.chatbot.commands.exceptions.InvalidCommandException;
import duke.chatbot.commands.exceptions.InvalidDeadlineException;
import duke.taskmanager.TaskManager;
import duke.taskmanager.task.DeadlineTask;

/**
 * Deadline Task Command Handler that adds a new Deadline task to the list of task
 * managed by the task manager.
 * Responds with the confirmation message stating that a new task is added.
 */
public class DeadlineTaskCommandHandler implements Command {
    private final TaskManager taskManager;
    /**
     * Creates a new handler for the deadline task command with a reference to the task manager
     *
     * @param taskManager a reference to the task manager
     */
    public DeadlineTaskCommandHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Adds a new deadline task to the task manager with the given attributes and responds with a confirmation message.
     *
     * @param arguments string of the attributes of the deadline task
     * @return the string of the response stating the successful addition of the new deadline task
     * @throws InvalidCommandException thrown when there are no arguments
     * @throws InvalidArgumentsException thrown when various invalid arguments are present
     */
    @Override
    public String execute(String arguments) throws InvalidCommandException, InvalidArgumentsException {
        if (arguments.length() == 0) {
            throw new InvalidCommandException();
        }

        String[] argumentList = arguments.split(DeadlineTask.TASK_DELIMITER);
        if (argumentList.length != 2) {
            throw new InvalidArgumentsException();
        }

        String deadlineTaskName = argumentList[0];
        String deadlineString = argumentList[1];
        LocalDateTime deadline;

        if (deadlineTaskName.length() == 0) {
            throw new EmptyTaskException();
        }

        try {
            deadline = LocalDateTime.parse(deadlineString, DateTimeFormatter.ofPattern(taskManager.getDateFormat()));
        } catch (DateTimeParseException exception) {
            throw new InvalidDeadlineException(taskManager.getDateFormat());
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("> Added: ");
        stringBuilder.append(taskManager.addTask(new DeadlineTask(deadlineTaskName, deadline)));
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
