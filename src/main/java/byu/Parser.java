package byu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;
import exceptions.ByuException;
import exceptions.EmptyDescriptionException;
import exceptions.IncorrectFileInputException;
import exceptions.InvalidDescriptionException;
import exceptions.InvalidInstructionException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 *  A parser that interprets a given string and translates them into commands.
 */
public class Parser {

    static final String DESCRIPTION_NOT_REQUIRED = "";
    static final DateTimeFormatter DATE_TIME_INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Returns the Command instance corresponding to the string.
     *
     * @param fullCommand the string to be parsed.
     */
    public static Command parse(String fullCommand) throws ByuException {
        Command command;
        Instruction instruction = getInstruction(fullCommand);
        String description = getDescription(instruction, fullCommand);
        switch (instruction) {
        case MARK:
            command = new MarkCommand(getDescriptionAsIntegerValue(Instruction.MARK, description));
            break;
        case UNMARK:
            command = new UnmarkCommand(getDescriptionAsIntegerValue(Instruction.UNMARK, description));
            break;
        case DEADLINE:
            command = prepareDeadline(description);
            break;
        case EVENT:
            command = prepareEvent(description);
            break;
        case TODO:
            command = prepareToDo(description);
            break;
        case DELETE:
            command = new DeleteCommand(getDescriptionAsIntegerValue(Instruction.DELETE, description));
            break;
        case BYE:
            command = new ExitCommand();
            break;
        case LIST:
            command = new ListCommand();
            break;
        case FIND:
            command = new FindCommand(description);
            break;
        default:
            throw new InvalidInstructionException();
        }
        return command;
    }

    /**
     * Returns the instruction in the command.
     *
     * @param fullCommand the string to be parsed.
     * @throws InvalidInstructionException if the instruction is invalid.
     */
    private static Instruction getInstruction(String fullCommand) throws InvalidInstructionException {
        String firstWord = fullCommand.split(" ")[0];
        try {
            return Instruction.valueOf(firstWord.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new InvalidInstructionException();
        }
    }

    /**
     * Returns the description in the command.
     *
     * @param i           the instruction in the command.
     * @param fullCommand the string to be parsed.
     * @throws EmptyDescriptionException if the description is empty.
     */
    private static String getDescription(Instruction i, String fullCommand) throws EmptyDescriptionException {
        switch (i) {
        case MARK:
        case UNMARK:
        case DEADLINE:
        case TODO:
        case EVENT:
        case DELETE:
        case FIND:
            String[] substringsSplitBySpace = fullCommand.split(" ");
            boolean hasDescription = substringsSplitBySpace.length > 1;
            if (!hasDescription) {
                throw new EmptyDescriptionException(i);
            }
            int startingIndexOfDescription = substringsSplitBySpace[0].length() + 1;
            return fullCommand.substring(startingIndexOfDescription);
        default:
            return DESCRIPTION_NOT_REQUIRED;
        }
    }

    /**
     * Returns an integer from the given description.
     *
     * @param d description of the command indicating index of a task.
     * @return an integer representing the index of a task.
     * @throws InvalidDescriptionException if the description cannot be converted to an integer.
     */
    private static int getDescriptionAsIntegerValue(Instruction i, String d) throws InvalidDescriptionException {
        try {
            return Integer.parseInt(d);
        } catch (NumberFormatException e) {
            throw new InvalidDescriptionException(i);
        }
    }

    /**
     * Returns an AddCommand instantiated with an Event.
     *
     * @param d description of the command indicating name and period of event.
     * @return an AddCommand instantiated with an Event.
     * @throws InvalidDescriptionException if the period cannot be interpreted.
     */
    private static AddCommand prepareEvent(String d) throws InvalidDescriptionException {
        String[] substrings = d.split(" /at ");
        if (substrings.length == 1) {
            throw new InvalidDescriptionException(Instruction.EVENT);
        } else {
            String name = substrings[0];
            String period = substrings[1];
            List<LocalDateTime> dateTimes = parsePeriod(period);
            Event event = new Event(name, dateTimes.get(0), dateTimes.get(1));
            return new AddCommand(event);
        }
    }

    /**
     * Parses the string representing the period of an event,
     * and returns a List containing the start and end time.
     *
     * @param period the period of the event.
     * @return a List containing the start time as first element and end time as second element.
     * @throws InvalidDescriptionException if the period cannot be parsed.
     */
    private static List<LocalDateTime> parsePeriod(String period) throws InvalidDescriptionException {
        String[] substrings = period.split(" to ");
        if (substrings.length != 2) {
            throw new InvalidDescriptionException(Instruction.EVENT);
        }
        String startDateTimeInput = substrings[0];
        String endDateTimeInput = substrings[1];
        LocalDateTime startDateTime = parseDateTimeInput(Instruction.EVENT, startDateTimeInput);
        LocalDateTime endDateTime = parseDateTimeInput(Instruction.EVENT, endDateTimeInput);
        return List.of(startDateTime, endDateTime);
    }

    /**
     * Parses the string representing the date and time,
     * and returns a LocalDateTime object.
     *
     * @param instruction the instruction of command.
     * @return a LocalDateTime object represented by the string.
     * @throws InvalidDescriptionException if dateTimeInput cannot be parsed.
     */
    private static LocalDateTime parseDateTimeInput(
            Instruction instruction, String dateTimeInput) throws InvalidDescriptionException {
        try {
            return LocalDateTime.parse(dateTimeInput, DATE_TIME_INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidDescriptionException(instruction);
        }
    }

    /**
     * Returns an AddCommand instantiated with a Deadline.
     *
     * @param d description of the AddCommand indicating name and dateTime of deadline.
     * @return an AddCommand instantiated with a Deadline.
     * @throws InvalidDescriptionException if the dateTime cannot be interpreted.
     */
    private static AddCommand prepareDeadline(String d) throws InvalidDescriptionException {
        String[] substrings = d.split(" /by ");
        if (substrings.length == 1) {
            throw new InvalidDescriptionException(Instruction.DEADLINE);
        }
        String name = substrings[0];
        String dateTimeInput = substrings[1];
        LocalDateTime dateTime = parseDateTimeInput(Instruction.DEADLINE, dateTimeInput);
        Deadline deadline = new Deadline(name, dateTime);
        return new AddCommand(deadline);
    }

    /**
     * Returns an AddCommand instantiated with a ToDo.
     *
     * @param d description of the command indicating name of todo.
     * @return an AddCommand instantiated with a ToDo.
     */
    private static AddCommand prepareToDo(String d) {
        ToDo todo = new ToDo(d);
        return new AddCommand(todo);
    }

    /**
     * Parses a line from the file to retrieve a task.
     *
     * @param line the line in a file to be parsed.
     * @return the task represented by the line.
     * @throws IncorrectFileInputException if the line cannot be parsed.
     */
    public static Task parseFileToTask(String line) throws IncorrectFileInputException {
        String[] details = line.split(" \\| ");
        String symbol = details[0];
        String isDoneValue = details[1];
        String taskName = details[2];
        Task task;
        switch (symbol) {
        case Deadline.SYMBOL:
            String dateTimeInput = details[3];
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeInput);
            task = new Deadline(taskName, dateTime);
            break;
        case Event.SYMBOL:
            String startDateTimeInput = details[3];
            String endDateTimeInput = details[4];
            LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeInput);
            LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeInput);
            task = new Event(taskName, startDateTime, endDateTime);
            break;
        case ToDo.SYMBOL:
            task = new ToDo(taskName);
            break;
        default:
            throw new IncorrectFileInputException();
        }
        if (isDoneValue.equals("1")) {
            task.setDone(true);
        }
        return task;
    }

}
