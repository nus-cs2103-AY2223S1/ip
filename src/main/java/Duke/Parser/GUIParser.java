package Duke.Parser;
import Duke.Commands.*;

import Duke.Exceptions.*;
import Duke.Tasks.Event;
import Duke.Tasks.TaskList;
import Duke.Tasks.ToDo;
import Duke.Tasks.Deadline;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Class that provides method to handle input for GUI.
 */
public class GUIParser {

    /**
     * Provides UserCommand from given input string and current tasks.
     * @param commandString Input String contain command.
     * @param userTasks TaskList of current tasks.
     * @return UserCommand formally.
     * @throws DukeException Exception may occur during this session, e.g. InvalidCommandException.
     */
    public UserCommand parseCommand(String commandString, TaskList userTasks) throws DukeException {
        int firstSpace = commandString.indexOf(" ");

        if (firstSpace == -1) {
            switch (commandString) {
            case "bye":
                return new QuitCommand();
            case "sort":
                return new SortAllCommand(userTasks);
            case "list":
                return new ListTasksCommand(userTasks);
            case "save" :
                return new SaveCommand(userTasks);
            default:
                throw new InvalidCommandException();
            }
        }

        String commandType = commandString.substring(0, firstSpace);
        String commandElse = commandString.substring(firstSpace).strip();

        switch (commandType) {
        case "todo" :
            return parseTodoCommand(commandElse, userTasks);
        case "deadline" :
            return parseDeadlineCommand(commandElse, userTasks);
        case "event" :
            return parseEventCommand(commandElse, userTasks);
        case "find":
            return parseFindTaskCommand(commandElse, userTasks);
        case "delete":
            return parseDeleteTaskCommand(commandElse, userTasks);
        case "done":
            return parseMarkDoneCommand(commandElse, userTasks);
        case "sort":
            return parseSortCommand(commandElse, userTasks);
        default:
            throw new InvalidCommandException();
        }

    }

    private SortDeadlineCommand parseSortCommand(String taskName, TaskList userTasks) throws TaskNotExistException {
        if (taskName.equals("Deadline"))
            return new SortDeadlineCommand(userTasks);
        else throw new TaskNotExistException();
    }

    private AddTaskCommand parseTodoCommand(String taskName, TaskList userTasks) {
        return new AddTaskCommand(new ToDo(taskName, false), userTasks);
    }

    private AddTaskCommand parseDeadlineCommand(String commandElse, TaskList userTasks) throws DeadlineException {
        try {
            String[] parts = commandElse.split("/");
            String description = parts[0].strip();
            String[] time = parts[1].strip().split(" ");
            LocalDate date = LocalDate.parse(time[0].strip());
            if (time.length == 1) {
                return new AddTaskCommand(
                        new Deadline(description, date, false),
                        userTasks);
            } else {
                LocalTime hourMinutes = LocalTime.parse(time[1].strip());
                return new AddTaskCommand(
                        new Deadline(description, date, hourMinutes, false),
                        userTasks);
            }
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new DeadlineException();
        }

    }

    private AddTaskCommand parseEventCommand(String comandElse, TaskList userTasks) throws EventException {
        try {
            String[] parts = comandElse.split("/");
            String description = parts[0].strip();
            String[] time = parts[1].strip().split(" ");
            return new AddTaskCommand(
                    new Event(description,
                            LocalDate.parse(time[0]),
                            LocalTime.parse(time[1]),
                            false),
                    userTasks);
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new EventException();
        }
    }

    private FindTaskCommand parseFindTaskCommand(String commandElse, TaskList userTasks) {
        return new FindTaskCommand(userTasks, commandElse.strip());
    }

    private DeleteTaskCommand parseDeleteTaskCommand(String commandElse, TaskList userTasks) throws TaskIndexException {
        try {
            return new DeleteTaskCommand(Integer.valueOf(commandElse), userTasks);
        } catch (NumberFormatException e) {
            throw new TaskIndexException();
        }
    }
    private MarkDoneCommand parseMarkDoneCommand(String commandElse, TaskList userTasks) throws TaskIndexException {
        try {
            return new MarkDoneCommand(Integer.valueOf(commandElse), userTasks);
        } catch (NumberFormatException e) {
            throw new TaskIndexException();
        }
    }

}
