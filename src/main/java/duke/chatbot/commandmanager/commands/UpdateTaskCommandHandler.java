package duke.chatbot.commandmanager.commands;

import java.util.Scanner;

import duke.chatbot.ChatBot;
import duke.chatbot.commandmanager.commands.exceptions.InvalidArgumentsException;
import duke.chatbot.commandmanager.commands.exceptions.InvalidCommandException;
import duke.chatbot.commandmanager.commands.exceptions.InvalidIndexException;
import duke.chatbot.commandmanager.commands.exceptions.NoSuchIndexException;
import duke.chatbot.taskmanager.task.DeadlineTask;
import duke.chatbot.taskmanager.task.EventTask;
import duke.chatbot.taskmanager.task.ToDoTask;

/**
 * Update Task Command handler that handles the updating of tasks in the
 * list of task managed by the task manager.
 */
public class UpdateTaskCommandHandler implements Command {
    private final ChatBot chatBot;
    /**
     * Creates a new handler for the update task command with a reference to the chatbot.
     *
     * @param chatBot a reference to the chatbot
     */
    public UpdateTaskCommandHandler(ChatBot chatBot) {
        this.chatBot = chatBot;
    }

    /**
     * Updates the task specified in the arguments with updated attributes.
     *
     * @param arguments string of the item number and updated attributes for the update task command
     * @return the string of the response stating the updated task
     * @throws InvalidCommandException thrown when there are no arguments
     * @throws InvalidArgumentsException thrown when various invalid arguments are present
     */
    @Override
    public String execute(String arguments) throws InvalidCommandException, InvalidArgumentsException {
        if (arguments.length() == 0) {
            throw new InvalidCommandException(this.chatBot.getPersonality());
        }

        Scanner argumentScanner = new Scanner(arguments);
        int itemNumber = 0;

        // Guard clause when an integer is not provided or when integer is out of range of the list size.
        try {
            itemNumber = Integer.parseInt(argumentScanner.next());
        } catch (NumberFormatException exception) {
            throw new InvalidIndexException(this.chatBot.getPersonality());
        }
        if (itemNumber <= 0 || itemNumber > this.chatBot.getTaskManager().getListSize()) {
            throw new NoSuchIndexException(this.chatBot.getPersonality());
        }

        if (!(argumentScanner.hasNext())) {
            throw new InvalidArgumentsException(this.chatBot.getPersonality());
        }

        String updatedTask = "";
        String updatedArguments = argumentScanner.nextLine().strip();
        switch (this.chatBot.getTaskManager().getTaskType(itemNumber)) {
        case ToDoTask.TASK_TYPE:
            updatedTask = new UpdateTodoTaskCommandHandler(this.chatBot).execute(itemNumber, updatedArguments);
            break;
        case DeadlineTask.TASK_TYPE:
            updatedTask = new UpdateDeadlineTaskCommandHandler(this.chatBot).execute(itemNumber, updatedArguments);
            break;
        case EventTask.TASK_TYPE:
            updatedTask = new UpdateEventTaskCommandHandler(this.chatBot).execute(itemNumber, updatedArguments);
            break;
        default:
            break;
        }
        String updatedTaskString = itemNumber + ") " + updatedTask;
        return this.chatBot.getPersonality().formulateResponse("update_task", updatedTaskString);
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
