package duke.chatbot.commandmanager.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.chatbot.commandmanager.commands.exceptions.EmptyDateFormatException;
import duke.chatbot.commandmanager.commands.exceptions.InvalidArgumentsException;
import duke.chatbot.commandmanager.commands.exceptions.InvalidEventException;
import duke.chatbot.personality.Personality;
import duke.chatbot.taskmanager.TaskManager;
import duke.chatbot.taskmanager.task.EventTask;

/**
 * Update Event Task Command handler that handles the updating of event tasks in the
 * list of task managed by the task manager.
 */
public class UpdateEventTaskCommandHandler implements UpdateCommand {
    private final Personality personality;
    private final TaskManager taskManager;
    /**
     * Creates a new handler for the update task command with a reference to the task manager
     * and the chatbot's personality.
     *
     * @param taskManager a reference to the chatbot's personality
     * @param taskManager a reference to the task manager
     */
    public UpdateEventTaskCommandHandler(Personality personality, TaskManager taskManager) {
        this.personality = personality;
        this.taskManager = taskManager;
    }

    /**
     * Updates the task name and the event time of the event task indicated by the item number.
     *
     * @param itemNumber the item number of the task to be updated
     * @param updatedArguments the updated task name and event time
     * @return the string of the updated task
     * @throws InvalidArgumentsException thrown when various invalid arguments are present
     */
    @Override
    public String execute(int itemNumber, String updatedArguments) throws InvalidArgumentsException {
        if (updatedArguments.length() == 0 || updatedArguments.equals(EventTask.TASK_DELIMITER)) {
            throw new InvalidArgumentsException(this.personality);
        }

        String[] argumentList = updatedArguments.split(EventTask.TASK_DELIMITER);
        if (argumentList.length > 2) {
            throw new InvalidArgumentsException(this.personality);
        }
        if (this.taskManager.getDateFormat().length() == 0) {
            throw new EmptyDateFormatException(this.personality);
        }

        String eventTaskName = "";
        String eventTimeString = "";

        if (argumentList.length == 1) {
            eventTaskName = argumentList[0].strip();
        } else if (argumentList.length == 2) {
            eventTaskName = argumentList[0].strip();
            eventTimeString = argumentList[1].strip();
            try {
                LocalDateTime.parse(eventTimeString, DateTimeFormatter.ofPattern(this.taskManager.getDateFormat()));
            } catch (DateTimeParseException exception) {
                throw new InvalidEventException(this.personality, this.taskManager.getDateFormat());
            }
        }

        return this.taskManager.updateTask(itemNumber,
                eventTaskName, eventTimeString, this.taskManager.getDateFormat());
    }
}
