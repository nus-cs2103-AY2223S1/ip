package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.CustomMessageException;
import duke.Responses;
import duke.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;

/**
 * Concrete class representing a command that add a task.
 */
public class AddTaskCommand extends CommandWithTasklistAndCommands {
    private final String command;
    private final int argLength;
    private final CommandType commandType;
    private final String splitBy;
    /**
     * @param arguments The arguments
     * @param command The original user command
     * @param argLength The length of the arguments
     * @param commandType The type of the command
     * @param splitBy The string to split the input by
     */
    public AddTaskCommand(String[] arguments,
                          String command, int argLength, CommandType commandType, String splitBy) {
        super(arguments);
        this.command = command;
        this.argLength = argLength;
        this.commandType = commandType;
        this.splitBy = splitBy;
    }

    @Override
    public String execute(Storage storage, TaskList taskList) throws CustomMessageException {
        if (argLength == 0) {
            throw new CustomMessageException((Responses.generateEmptyDescMessage(commandType.getString())));
        }
        assert argLength > 0 : "There should be minimally 1 arguments to a new task";
        if (commandType == CommandType.TODO) {
            taskList.addToTaskList(new ToDo(command.substring(5).strip(), CommandType.TODO));
        } else if (commandType == CommandType.EVENT || commandType == CommandType.DEADLINE) {
            String[] splitString = command.split(splitBy);
            String taskDescription = splitString[0].substring(commandType.getString().length() + 1).strip();
            if (taskDescription.isEmpty() || taskDescription.isBlank()) {
                throw new CustomMessageException((Responses.generateEmptyDescMessage(commandType.getString())));
            }
            String userRequirement = splitString[1].strip();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(userRequirement, formatter);
            Task newTask;
            if (commandType == CommandType.EVENT) {
                newTask = new Event(taskDescription, CommandType.EVENT, dateTime);
            } else {
                newTask = new Deadline(taskDescription, CommandType.DEADLINE, dateTime);
            }
            taskList.addToTaskList(newTask);
        }

        saveTaskListToStorage(storage, taskList);
        return (("Got it. I've added this task:\n    "
                + taskList.getTaskString(taskList.sizeOfList() - 1) + "\n"
                + Responses.generateTasksNumberMessage(taskList)));
    }
}

