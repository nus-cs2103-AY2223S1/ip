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
    private final CommandType commandType;
    private final String splitBy;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * @param arguments   The arguments
     * @param command     The original user command
     * @param commandType The type of the command
     * @param splitBy     The string to split the input by
     */
    public AddTaskCommand(String[] arguments,
                          String command, CommandType commandType, String splitBy) {
        super(arguments);
        this.command = command;
        this.commandType = commandType;
        this.splitBy = splitBy;
    }

    @Override
    public String execute(Storage storage, TaskList taskList) throws CustomMessageException {
        assert arguments.length > 0 : "There should be minimally 1 arguments to a new task";
        addNewTaskToTaskList(taskList);
        saveTaskListToStorageAndUnlockUndo(storage, taskList);
        return (("Got it. I've added this task:\n    "
                + taskList.getTaskString(taskList.sizeOfList() - 1) + "\n"
                + Responses.generateTasksNumberMessage(taskList)));
    }

    private void addNewTaskToTaskList(TaskList taskList) throws CustomMessageException {
        if (commandType == CommandType.TODO) {
            taskList.addToTaskList(new ToDo(command.substring(5).strip(), CommandType.TODO));
        } else {
            String[] splitString = command.split(splitBy);
            String taskDescription = getTaskDescription(splitString);
            Task newTask = getNewEventOrDeadline(taskDescription, getParsedDateTime(splitString[1]));
            taskList.addToTaskList(newTask);
        }
    }

    private LocalDateTime getParsedDateTime(String userDateTime) {
        return LocalDateTime.parse(userDateTime.strip(), formatter);
    }

    private Task getNewEventOrDeadline(String taskDescription, LocalDateTime dateTime) {
        Task newTask;
        if (commandType == CommandType.EVENT) {
            newTask = new Event(taskDescription, CommandType.EVENT, dateTime);
        } else {
            newTask = new Deadline(taskDescription, CommandType.DEADLINE, dateTime);
        }
        return newTask;
    }

    private String getTaskDescription(String[] splitString) throws CustomMessageException {
        String taskDescription = splitString[0].substring(commandType.getString().length()).strip();
        if (taskDescription.isEmpty() || taskDescription.isBlank()) {
            throw new CustomMessageException((Responses.generateEmptyDescMessage(commandType.getString())));
        }
        return taskDescription;
    }
}

