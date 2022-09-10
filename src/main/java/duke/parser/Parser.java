package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DueCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.PriorityCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.exception.InvalidDateException;
import duke.exception.InvalidIndexException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidPriorityException;
import duke.exception.MissingDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Priority;
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
            + "HH:mm\nor YYYY-MM-DD for deadlines\nand YYYY-MM-DD HH:mm-HH:mm for events";
    /**
     * Parses the user input and returns a command if the input is valid.
     *
     * @param input The given user input.
     * @return The specified command of the user.
     * @throws MissingDescriptionException The exception that occurs
     *     when the user did not input the command fully.
     * @throws InvalidInputException The exception that occurs when the user did not input any valid command.
     */
    public Command parse(String input) {
        String[] str = input.split("\\s+", 2);
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
        case PriorityCommand.COMMAND_WORD:
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
            command = prepareTasksCommand(str, TaskType.TODO);
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
        case PriorityCommand.COMMAND_WORD: {
            command = preparePriorityCommand(str);
            break;
        }
        default:
            throw new InvalidInputException();
        }
        return command;
    }


    private Command prepareTasksCommand(String args, TaskType type) {
        try {
            String[] components = args.split("\\s+", 2);
            Task task;
            if (components.length == 1) {
                task = createTaskWithPriority(components[0], "", type);;
            } else {
                task = createTaskWithPriority(components[0], components[1], type);
            }
            switch (type) {
            case TODO:
                return new ToDoCommand(task);
            case DEADLINE:
                return new DeadlineCommand(task);
            case EVENT:
                return new EventCommand(task);
            default:
                assert false;
                break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidInputException();
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(INVALID_DATE_MESSAGE);
        }
        assert false;
        throw new InvalidInputException();
    }

    private Command prepareDueCommand(String input) {
        try {
            LocalDate date = LocalDate.parse(input);
            return new DueCommand(date);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(INVALID_FIND_DATE_MESSAGE);
        }
    }

    private Command preparePriorityCommand(String input) {
        try {
            String[] components = input.split("\\s+", 2);
            int index = parseIntegerString(components[0]);
            if (components[1].equals("")) {
                throw new MissingDescriptionException("priority", " is incomplete");
            }
            Priority priority = Priority.valueOf(components[1].toUpperCase());
            return new PriorityCommand(index, priority);
        } catch (IllegalArgumentException e) {
            throw new InvalidPriorityException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingDescriptionException("priority", " is incomplete");
        }
    }

    private Task createTaskWithPriority(String component1, String component2, TaskType type) {
        Task task;
        Priority priority = null;
        String description = "";
        if (Priority.checkPriority(component1)) {
            priority = Priority.valueOf(component1.toUpperCase());
            description = component2;
        } else {
            description = String.join(" ", component1, component2);
        }
        switch (type) {
        case TODO:
            if (priority != null) {
                task = new ToDo(component2, TaskType.TODO, priority);
            } else {
                task = new ToDo(description, TaskType.TODO);
            }
            return task;
        case DEADLINE:
            String[] str = description.split(" /by ", 2);
            task = createTaskWithDate(Objects.requireNonNullElse(priority, Priority.LOW), str[0], str[1], type);
            return task;
        case EVENT:
            String[] str1 = description.split(" /at ", 2);
            task = createTaskWithDate(Objects.requireNonNullElse(priority, Priority.LOW), str1[0], str1[1], type);
            return task;
        default:
            assert false;
            break;
        }
        assert false;
        return null;
    }

    private Task createTaskWithDate(Priority priority, String description, String timePeriod, TaskType type) {
        String[] timeComponents;
        LocalTime time = null;
        LocalDate date;
        Task task = null;
        switch (type) {
        case DEADLINE:
            timeComponents = timePeriod.split("\\s+", 2);
            date = LocalDate.parse(timeComponents[0]);
            if (timeComponents.length != 1 && !timeComponents[1].equals("")) {
                time = LocalTime.parse(timeComponents[1].trim());
            }
            task = new Deadline(description, date, time, type, priority);
            break;
        case EVENT:
            LocalTime timeEnd;
            timeComponents = timePeriod.split("\\s+", 2);
            date = LocalDate.parse(timeComponents[0]);
            String[] duration = timeComponents[1].split("-", 2);
            if (duration.length == 1) {
                throw new DateTimeParseException("", duration[0], 1);
            } else {
                time = LocalTime.parse(duration[0]);
                timeEnd = LocalTime.parse(duration[1].trim());
            }
            task = new Event(description, date, time, timeEnd, type, priority);
            break;
        default:
            assert false;
            break;
        }
        return task;
    }

    private int parseIntegerString(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(INVALID_INTEGER_MESSAGE);
        }
    }
    /**
     * Converts the given taskList into a list of strings to be written into a file.
     *
     * @param taskList The given taskList containing tasks to be saved.
     * @return An arraylist of strings to be written.
     */
    public ArrayList<String> writeFileContents(ArrayList<Task> taskList) {
        ArrayList<String> list = new ArrayList<>();
        for (Task task : taskList) {
            String string = task.getPriority().name() + " | "
                    + task.getDoneStatus() + " | " + task.getDescription().trim();
            switch (task.type) {
            case TODO: {
                string = "T | " + string;
                break;
            }
            case DEADLINE: {
                string = "D | " + string;
                break;
            }
            case EVENT: {
                string = "E | " + string;
                break;
            }
            default:
                assert false;
                break;
            }
            list.add(string);
        }
        return list;
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
                assert false;
                break;
            }
            listOfTasks.add(task);
        }
        return listOfTasks;
    }

    private Task createTaskFromFile(String input, TaskType type) {
        Task task = null;
        int taskStatus = 0;
        String[] components;
        switch (type) {
        case TODO: {
            components = input.split(" \\| ", 3);
            task = new ToDo(components[2], type, Priority.valueOf(components[0]));
            taskStatus = Integer.parseInt(components[1]);
            break;
        }
        case DEADLINE:
        case EVENT: {
            components = input.split(" \\| ", 4);
            task = createTaskWithDate(Priority.valueOf(components[0]), components[2], components[3], type);
            taskStatus = Integer.parseInt(components[1]);
            break;
        }
        default:
            assert false;
            break;
        }
        assert task != null;
        if (taskStatus == 1) {
            task.markAsDone();
        }
        return task;
    }
}
