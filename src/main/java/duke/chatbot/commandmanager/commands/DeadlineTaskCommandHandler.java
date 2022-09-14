package duke.chatbot.commandmanager.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.chatbot.commandmanager.commands.exceptions.EmptyTaskException;
import duke.chatbot.commandmanager.commands.exceptions.InvalidArgumentsException;
import duke.chatbot.commandmanager.commands.exceptions.InvalidCommandException;
import duke.chatbot.commandmanager.commands.exceptions.InvalidDeadlineException;
import duke.chatbot.personality.Personality;
import duke.chatbot.taskmanager.TaskManager;
import duke.chatbot.taskmanager.task.DeadlineTask;

/**
 * Deadline Task Command Handler that adds a new Deadline task to the list of task
 * managed by the task manager.
 * Responds with the confirmation message stating that a new task is added.
 */
public class DeadlineTaskCommandHandler implements Command {
    private final Personality personality;
    private final TaskManager taskManager;
    /**
     * Creates a new handler for the deadline task command with a reference to the task manager
     * and the chatbot's personality.
     *
     * @param taskManager a reference to the chatbot's personality
     * @param taskManager a reference to the task manager
     */
    public DeadlineTaskCommandHandler(Personality personality, TaskManager taskManager) {
        this.personality = personality;
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
            throw new InvalidCommandException(this.personality);
        }

        String[] argumentList = arguments.split(DeadlineTask.TASK_DELIMITER);
        if (argumentList.length != 2) {
            throw new InvalidArgumentsException(this.personality);
        }

        String deadlineTaskName = argumentList[0].strip();
        String deadlineString = argumentList[1].strip();
        LocalDateTime deadline;

        if (deadlineTaskName.length() == 0) {
            throw new EmptyTaskException(this.personality);
        }

        try {
            deadline = LocalDateTime.parse(deadlineString, DateTimeFormatter.ofPattern(taskManager.getDateFormat()));
        } catch (DateTimeParseException exception) {
            throw new InvalidDeadlineException(this.personality, taskManager.getDateFormat());
        }

        String taskAdded = taskManager.addTask(new DeadlineTask(deadlineTaskName, deadline));
        return personality.formulateResponse("add_task", taskAdded);
    }
}
