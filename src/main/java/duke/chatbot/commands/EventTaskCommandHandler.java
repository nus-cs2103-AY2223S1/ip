package duke.chatbot.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.chatbot.commands.exceptions.EmptyTaskException;
import duke.chatbot.commands.exceptions.InvalidArgumentsException;
import duke.chatbot.commands.exceptions.InvalidCommandException;
import duke.chatbot.commands.exceptions.InvalidDeadlineException;
import duke.taskmanager.TaskManager;
import duke.taskmanager.task.EventTask;

/**
 * Event Task Command Handler that adds a new Event task to the list of task
 * managed by the task manager.
 * Responds with the confirmation message stating that a new task is added.
 */
public class EventTaskCommandHandler implements Command {
    private final TaskManager taskManager;
    /**
     * Creates a new handler for the event task command with a reference to the task manager
     *
     * @param taskManager a reference to the task manager
     */
    public EventTaskCommandHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public String execute(String arguments) throws InvalidCommandException, InvalidArgumentsException {
        if (arguments.length() == 0) {
            throw new InvalidCommandException();
        }

        String[] argumentList = arguments.split(EventTask.TASK_DELIMITER);
        if (argumentList.length != 2) {
            throw new InvalidArgumentsException();
        }

        String eventTaskName = argumentList[0];
        String eventTimeString = argumentList[1];
        LocalDateTime eventTime;

        if (eventTaskName.length() == 0) {
            throw new EmptyTaskException();
        }

        try {
            eventTime = LocalDateTime.parse(eventTimeString, DateTimeFormatter.ofPattern(taskManager.getDateFormat()));
        } catch (DateTimeParseException exception) {
            throw new InvalidDeadlineException(taskManager.getDateFormat());
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("> Added: ");
        stringBuilder.append(taskManager.addTask(new EventTask(eventTaskName, eventTime)));
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
