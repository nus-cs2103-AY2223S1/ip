package duke.chatbot.commandmanager.commands;

import java.util.Scanner;

import duke.chatbot.commandmanager.commands.exceptions.InvalidArgumentsException;
import duke.chatbot.commandmanager.commands.exceptions.InvalidCommandException;
import duke.chatbot.commandmanager.commands.exceptions.InvalidIndexException;
import duke.chatbot.commandmanager.commands.exceptions.NoSuchIndexException;
import duke.chatbot.taskmanager.TaskManager;
import duke.chatbot.taskmanager.task.DeadlineTask;
import duke.chatbot.taskmanager.task.EventTask;
import duke.chatbot.taskmanager.task.ToDoTask;

/**
 * Update Task Command handler that handles the updating of tasks in the
 * list of task managed by the task manager.
 */
public class UpdateTaskCommandHandler implements Command {
    private TaskManager taskManager;
    /**
     * Creates a new handler for the update task command with a reference to the task manager.
     *
     * @param taskManager a reference to the task manager
     */
    public UpdateTaskCommandHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
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
            throw new InvalidCommandException();
        }

        Scanner argumentScanner = new Scanner(arguments);
        int itemNumber = 0;

        // Guard clause when an integer is not provided or when integer is out of range of the list size.
        try {
            itemNumber = Integer.parseInt(argumentScanner.next());
        } catch (NumberFormatException exception) {
            throw new InvalidIndexException();
        }
        if (itemNumber <= 0 || itemNumber > this.taskManager.getListSize()) {
            throw new NoSuchIndexException();
        }

        if (!(argumentScanner.hasNext())) {
            throw new InvalidArgumentsException();
        }

        String updatedTaskString = "";
        String updatedArguments = argumentScanner.nextLine().strip();
        switch (this.taskManager.getTaskType(itemNumber)) {
        case ToDoTask.TASK_TYPE:
            updatedTaskString = new UpdateTodoTaskCommandHandler(this.taskManager)
                    .execute(itemNumber, updatedArguments);
            break;

        case DeadlineTask.TASK_TYPE:
            updatedTaskString = new UpdateDeadlineTaskCommandHandler(this.taskManager)
                    .execute(itemNumber, updatedArguments);
            break;
        case EventTask.TASK_TYPE:
            updatedTaskString = new UpdateEventTaskCommandHandler(this.taskManager)
                    .execute(itemNumber, updatedArguments);
            break;
        default:
            break;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The following item has been updated.\n");
        stringBuilder.append(itemNumber).append(") ").append(updatedTaskString);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
