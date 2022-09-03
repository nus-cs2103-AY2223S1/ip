package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DueCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.exception.InvalidDateException;
import duke.exception.InvalidIndexException;
import duke.exception.InvalidInputException;
import duke.exception.MissingDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.ToDo;

/**
 * A component of the chatBot Duke that deciphers the user inputs.
 */
public class Parser {
    private static final String INVALID_INTEGER_MESSAGE = "please input a valid integer";
    private static final String INVALID_FIND_DATE_MESSAGE = "Input a valid date in the format YYYY-MM-DD";
    private static final String INVALID_DATE_MESSAGE = "Input valid date in the format YYYY-MM-DD "
            + "HH:mm\nor YYYY-MM-DD for deadlines and YYYY-MM-DD HH:mm-HH:mm for events";
    /**
     * Parses the user input and returns a command if the input is valid.
     *
     * @param input The given user input.
     * @return The specified command of the user.
     * @throws MissingDescriptionException The exception that occurs.
     *     when the user did not input the command fully.
     * @throws InvalidInputException The exception that occurs when the user did not input any valid command.
     */
    public Command parse(String input) {
        String[] str = input.split(" ", 2);
        String commandWord = str[0];
        Command command;
        if (str.length == 1 || str[1].trim().equals("")) {
            command = parseOneComponentCommands(commandWord);
        } else {
            command = parseTwoComponentCommands(commandWord, str[1]);
        }
        return command;
    }

    private Command parseOneComponentCommands(String commandWord) {
        Command command;
        switch (commandWord) {
        case ExitCommand.COMMAND_WORD: {
            command = new ExitCommand();
            break;
        }
        case ListCommand.COMMAND_WORD: {
            command = new ListCommand();
            break;
        }
        case DueCommand.COMMAND_WORD:
        case MarkCommand.COMMAND_WORD:
        case DeadlineCommand.COMMAND_WORD:
        case UnmarkCommand.COMMAND_WORD:
        case ToDoCommand.COMMAND_WORD:
        case EventCommand.COMMAND_WORD:
        case DeleteCommand.COMMAND_WORD:
        case FindCommand.COMMAND_WORD:
            throw new MissingDescriptionException(commandWord);
        default:
            throw new InvalidInputException();
        }
        return command;
    }

    private Command parseTwoComponentCommands(String commandWord, String str) {
        Command command;
        switch (commandWord) {
        case MarkCommand.COMMAND_WORD: {
            command = new MarkCommand(parseIntegerString(str.trim()));
            break;
        }
        case UnmarkCommand.COMMAND_WORD: {
            command = new UnmarkCommand(parseIntegerString(str.trim()));
            break;
        }
        case ToDoCommand.COMMAND_WORD: {
            command = new ToDoCommand(new ToDo(str, TaskType.TODO));
            break;
        }
        case DeadlineCommand.COMMAND_WORD: {
            command = prepareTasksCommand(str, TaskType.DEADLINE);
            break;
        }
        case EventCommand.COMMAND_WORD: {
            command = prepareTasksCommand(str, TaskType.EVENT);
            break;
        }
        case DeleteCommand.COMMAND_WORD: {
            command = new DeleteCommand(parseIntegerString(str.trim()));
            break;
        }
        case DueCommand.COMMAND_WORD: {
            command = prepareDueCommand(str);
            break;
        }
        case FindCommand.COMMAND_WORD: {
            command = new FindCommand(str.split("\\s+"));
            break;
        }
        default:
            throw new InvalidInputException();
        }
        return command;
    }

    private int parseIntegerString(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(INVALID_INTEGER_MESSAGE);
        }
    }

    private Command prepareTasksCommand(String args, TaskType type) {
        try {
            String[] components;
            Task task;
            switch (type) {
            case DEADLINE:
                components = args.split("/by ", 2);
                task = createTaskWithDate(components[0], components[1], type);
                return new DeadlineCommand(task);
            case EVENT:
                components = args.split("/at ", 2);
                task = createTaskWithDate(components[0], components[1], type);
                return new EventCommand(task);
            default:
                break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputException();
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(INVALID_DATE_MESSAGE);
        }
        throw new InvalidInputException();
    }

    private Task createTaskWithDate(String description, String timePeriod, TaskType type) {
        LocalTime time = null;
        LocalDate date;
        Task task = null;
        String[] components = timePeriod.split(" ", 2);
        date = LocalDate.parse(components[0]);
        switch (type) {
        case DEADLINE:
            if (components.length != 1 && !components[1].trim().equals("")) {
                time = LocalTime.parse(components[1]);
            }
            task = new Deadline(description, date, time, type);
            break;
        case EVENT:
            LocalTime timeEnd;
            if (components.length == 1) {
                throw new DateTimeParseException("", components[0], 1);
            } else {
                String[] duration = components[1].split("-", 2);
                time = LocalTime.parse(duration[0]);
                timeEnd = LocalTime.parse(duration[1]);
            }
            task = new Event(description, date, time, timeEnd, type);
            break;
        default:
            break;
        }
        return task;
    }

    private Command prepareDueCommand(String input) {
        try {
            LocalDate date = LocalDate.parse(input);
            return new DueCommand(date);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(INVALID_FIND_DATE_MESSAGE);
        }
    }

    /**
     * Parses the data from the file into an arraylist containing the saved tasks.
     *
     * @param contents The list of strings that is read from the file.
     * @return An arraylist containing the saved tasks.
     */
    public ArrayList<Task> parseFileContents(List<String> contents) {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        for (String content : contents) {
            String[] components = content.split(" \\| ", 2);
            Task task = null;
            switch (components[0]) {
            case "T": {
                task = createTaskFromFile(components[1], TaskType.TODO);
                break;
            }
            case "D": {
                task = createTaskFromFile(components[1], TaskType.DEADLINE);
                break;
            }
            case "E": {
                task = createTaskFromFile(components[1], TaskType.EVENT);
                break;
            }
            default:
                break;
            }
            if (task != null) {
                listOfTasks.add(task);
            }
        }
        return listOfTasks;
    }

    /**
     * Converts the given taskList into a list of strings to be written into a file.
     *
     * @param taskList The given taskList containing tasks to be saved.
     * @return An arraylist of strings to be written.
     */
    public ArrayList<String> writeFileContents(ArrayList<Task> taskList) {
        ArrayList<String> list = new ArrayList<>();
        String string = "";
        for (Task task : taskList) {
            switch (task.type) {
            case TODO: {
                string = "T | " + task.getDoneStatus() + " | " + task.getDescription();
                break;
            }
            case DEADLINE: {
                string = "D | " + task.getDoneStatus() + " | " + task.getDescription();
                break;
            }
            case EVENT: {
                string = "E | " + task.getDoneStatus() + " | " + task.getDescription();
                break;
            }
            default:
                string = "";
                break;
            }
            if (!string.equals("")) {
                list.add(string);
            }
        }
        return list;
    }
    private Task createTaskFromFile(String input, TaskType type) {
        Task task = null;
        int taskStatus = 0;
        String[] components;
        switch (type) {
        case TODO: {
            components = input.split(" \\| ", 2);
            task = new ToDo(components[1], type);
            taskStatus = Integer.parseInt(components[0]);
            break;
        }
        case DEADLINE:
        case EVENT: {
            components = input.split(" \\| ", 3);
            task = createTaskWithDate(components[1], components[2], type);
            taskStatus = Integer.parseInt(components[0]);
            break;
        }
        default:
            break;
        }
        if (task != null & taskStatus == 1) {
            task.markAsDone();
        }
        return task;
    }
}
