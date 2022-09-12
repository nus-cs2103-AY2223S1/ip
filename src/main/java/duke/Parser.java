package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * A class to parse user input
 */
public class Parser {
    private final TaskList taskList;

    /**
     * Takes in a list of the tasks where the parsed tasks will be stored.
     *
     * @param taskList A list of the tasks
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    private static String generateEmptyDescMessage(String taskType) {
        return "OOPS!!! The description of a " + taskType + " cannot be empty.\n";
    }

    private static String generateInvalidArgumentsMessage(String action) {
        return "OOPS!!! The " + action + " must valid arguments.\n";
    }

    private String generateTasksNumberMessage() {
        return "Now you have " + taskList.sizeOfList() + " task" + (taskList.sizeOfList() == 1 ? "" : "s")
                + " in the list.";
    }

    /**
     * Returns a {@code String} representing the parsed input
     *
     * @param command            The user input
     * @return A {@code String} representing the input
     * @throws CustomMessageException if invalid input is given
     */
    public String parseUserCommand(String command)
            throws CustomMessageException {
        String[] commands = command.split("\\s+");
        Command taskType;
        try {
            taskType = Command.valueOf(commands[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CustomMessageException(("OOPS!!! I'm sorry, but I "
                    + "don't know what that means :-("));
        }
        commands = Arrays.copyOfRange(commands, 1, commands.length);
        if (!taskType.isNumberOfArgsCorrect(commands.length)) {
            throw new CustomMessageException((generateInvalidArgumentsMessage(taskType.getString())));
        }
        int index;
        String displayString;
        switch (taskType) {
        case BYE:
            displayString = Responses.BYE_MESSAGE;
            break;
        case LIST:
            String tasks = taskList.getTextRepresentationOfAllTasks();
            displayString = tasks.length() > 0 ? "Here are the tasks in your list:"
                    + tasks : "No existing tasks found :(";
            break;
        case MARK:
            index = Integer.parseInt(commands[0]) - 1;
            taskList.markTaskAsDone(index);
            displayString = "Nice! I've marked this task as done:\n    " + taskList.getTaskString(index);
            break;
        case UNMARK:
            index = Integer.parseInt(commands[0]) - 1;
            taskList.markTaskAsNotDone(index);
            displayString = "OK, I've marked this task as not done yet:\n    "
                    + taskList.getTaskString(index);
            break;
        case DELETE:
            index = Integer.parseInt(commands[0]) - 1;
            String deletedTaskDescription = taskList.getTaskString(index);
            taskList.removeTask(index);
            displayString = "Noted. I've removed this task:\n    "
                    + deletedTaskDescription + "\n" + generateTasksNumberMessage();
            break;
        case TODO:
            displayString = parseNewTaskCommand(command, commands.length, Command.TODO, "");
            break;
        case DEADLINE:
            displayString = parseNewTaskCommand(command, commands.length, Command.DEADLINE, " /by ");
            break;
        case EVENT:
            displayString = parseNewTaskCommand(command, commands.length, Command.EVENT, " /at ");
            break;
        case FIND:
            displayString = ("Here are the matching tasks in your list:"
                    + taskList.getTextRepresentationOfKeywordTasks(commands[0]));
            break;
        default:
            throw new CustomMessageException((
                    "OOPS!!! I'm sorry, but I don't know what that means :-(\n"));
        }
        return displayString.equals("") ? "" : displayString + "\n";
    }

    private String parseNewTaskCommand(String command, int commandsLen, Command taskCommand, String toSplitBy)
            throws CustomMessageException {
        if (commandsLen == 0) {
            throw new CustomMessageException((generateEmptyDescMessage(taskCommand.getString())));
        }
            assert commandsLen > 0 : "There should be minimally 1 arguments to a new task";
        if (taskCommand == Command.TODO) {
            taskList.addToTaskList(new ToDo(command.substring(5).strip(), duke.Command.TODO));
        } else if (taskCommand == Command.EVENT || taskCommand == Command.DEADLINE) {
            String[] splitString = command.split(toSplitBy);
            String taskDescription = splitString[0].substring(taskCommand.getString().length() + 1).strip();
            if (taskDescription.isEmpty() || taskDescription.isBlank()) {
                throw new CustomMessageException((generateEmptyDescMessage(taskCommand.getString())));
            }
            String userRequirement = splitString[1].strip();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(userRequirement, formatter);
            Task newTask;
            if (taskCommand == Command.EVENT) {
                newTask = new Event(taskDescription, Command.EVENT, dateTime);
            } else {
                newTask = new Deadline(taskDescription, Command.DEADLINE, dateTime);
            }
            taskList.addToTaskList(newTask);
        }
        return (("Got it. I've added this task:\n    "
                + taskList.getTaskString(taskList.sizeOfList() - 1) + "\n" + generateTasksNumberMessage()));
    }
}
