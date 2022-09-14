package duke.chatbot.commandmanager.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.chatbot.commandmanager.commands.exceptions.EmptyDateFormatException;
import duke.chatbot.commandmanager.commands.exceptions.InvalidArgumentsException;
import duke.chatbot.commandmanager.commands.exceptions.InvalidDeadlineException;
import duke.chatbot.personality.Personality;
import duke.chatbot.taskmanager.TaskManager;
import duke.chatbot.taskmanager.task.DeadlineTask;

/**
 * Update Deadline Task Command handler that handles the updating of deadline tasks in the
 * list of task managed by the task manager.
 */
public class UpdateDeadlineTaskCommandHandler implements UpdateCommand {
    private final Personality personality;
    private final TaskManager taskManager;
    /**
     * Creates a new handler for the update task command with a reference to the task manager
     * and the chatbot's personality.
     *
     * @param personality a reference to the chatbot's personality
     * @param taskManager a reference to the task manager
     */
    public UpdateDeadlineTaskCommandHandler(Personality personality, TaskManager taskManager) {
        this.personality = personality;
        this.taskManager = taskManager;
    }

    /**
     * Updates the task name and the deadline of the  deadline task indicated by the item number.
     *
     * @param itemNumber the item number of the task to be updated
     * @param updatedArguments the updated task name and deadline
     * @return the string of the updated task
     * @throws InvalidArgumentsException thrown when various invalid arguments are present
     */
    @Override
    public String execute(int itemNumber, String updatedArguments) throws InvalidArgumentsException {
        if (updatedArguments.length() == 0 || updatedArguments.equals(DeadlineTask.TASK_DELIMITER)) {
            throw new InvalidArgumentsException(this.personality);
        }

        String[] argumentList = updatedArguments.split(DeadlineTask.TASK_DELIMITER);
        if (argumentList.length > 2) {
            throw new InvalidArgumentsException(this.personality);
        }
        if (this.taskManager.getDateFormat().length() == 0) {
            throw new EmptyDateFormatException(this.personality);
        }

        String deadlineTaskName = "";
        String deadlineString = "";

        if (argumentList.length == 1) {
            deadlineTaskName = argumentList[0].strip();
        } else if (argumentList.length == 2) {
            deadlineTaskName = argumentList[0].strip();
            deadlineString = argumentList[1].strip();
            try {
                LocalDateTime.parse(deadlineString, DateTimeFormatter.ofPattern(this.taskManager.getDateFormat()));
            } catch (DateTimeParseException exception) {
                throw new InvalidDeadlineException(this.personality, this.taskManager.getDateFormat());
            }
        }

        return this.taskManager.updateTask(itemNumber,
                deadlineTaskName, deadlineString, this.taskManager.getDateFormat());
    }
}
