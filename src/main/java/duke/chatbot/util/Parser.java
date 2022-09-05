package duke.chatbot.util;

import static duke.chatbot.common.DateFormat.DATE_INPUT_FORMAT;
import static duke.chatbot.common.DateFormat.DATE_TIME_INPUT_FORMAT;
import static duke.chatbot.common.Message.MESSAGE_INVALID_DATE_FORMAT;
import static duke.chatbot.common.Message.MESSAGE_INVALID_DATE_TIME_FORMAT;
import static duke.chatbot.common.Message.MESSAGE_TOO_MANY_ARGUMENTS;
import static duke.chatbot.common.Message.MESSAGE_UNEXPECTED;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.chatbot.command.AddDeadlineCommand;
import duke.chatbot.command.AddEventCommand;
import duke.chatbot.command.AddToDoCommand;
import duke.chatbot.command.CheckDateCommand;
import duke.chatbot.command.Command;
import duke.chatbot.command.DeleteCommand;
import duke.chatbot.command.ExitCommand;
import duke.chatbot.command.FindKeywordCommand;
import duke.chatbot.command.InvalidInputCommand;
import duke.chatbot.command.ListCommand;
import duke.chatbot.command.MarkCommand;
import duke.chatbot.command.UnmarkCommand;
import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.exception.InvalidStorageFileException;
import duke.chatbot.data.task.Deadline;
import duke.chatbot.data.task.Event;
import duke.chatbot.data.task.TaskList;
import duke.chatbot.data.task.ToDo;

/**
 * A parser class to extract information from strings.
 *
 * @author jq1836
 */
public class Parser {
    /**
     * A Java regex pattern for basic commands.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile(
            "(?<commandWord>\\S+)(?<arguments>.*)"
    );

    /**
     * Returns an instance of {@link Command}, which contains all the arguments of the command string parsed in an
     * instance of list of string.
     *
     * @param userInput The string to be parsed.
     * @return An instance of Command which corresponds to the command string parsed.
     * @throws InvalidInputException If line is not a valid command string.
     */
    public static Command parseCommand(String userInput) throws InvalidInputException {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            return new InvalidInputCommand(MESSAGE_UNEXPECTED);
        }

        String commandWord = matcher.group("commandWord").strip();
        String arguments = matcher.group("arguments").strip();

        switch (commandWord) {
        // Add task commands
        case AddToDoCommand.COMMAND_WORD:
            return new AddToDoCommand(arguments);
        case AddDeadlineCommand.COMMAND_WORD:
            return new AddDeadlineCommand(arguments);
        case AddEventCommand.COMMAND_WORD:
            return new AddEventCommand(arguments);

        // Filter query commands
        case FindKeywordCommand.COMMAND_WORD:
            return new FindKeywordCommand(arguments);
        case CheckDateCommand.COMMAND_WORD:
            return new CheckDateCommand(arguments);

        // Single integer argument commands
        case MarkCommand.COMMAND_WORD:
            return new MarkCommand(arguments);
        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommand(arguments);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(arguments);

        // No argument commands
        case ListCommand.COMMAND_WORD:
            return arguments.isEmpty()
                    ? new ListCommand()
                    : new InvalidInputCommand(MESSAGE_TOO_MANY_ARGUMENTS);
        case ExitCommand.COMMAND_WORD:
            return arguments.isEmpty()
                    ? new ExitCommand()
                    : new InvalidInputCommand(MESSAGE_TOO_MANY_ARGUMENTS);

        default:
            return new InvalidInputCommand(MESSAGE_UNEXPECTED);
        }
    }

    /**
     * Returns an instance of LocalDateTime that corresponds to the string parsed.
     *
     * @param dateTime A string containing date and time information.
     * @return An instance of LocalDateTime that corresponds to the string parsed.
     * @throws InvalidInputException If the argument string does not follow the format.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws InvalidInputException {
        try {
            return LocalDateTime.parse(dateTime, DATE_TIME_INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(MESSAGE_INVALID_DATE_TIME_FORMAT);
        }
    }

    /**
     * Returns an instance of LocalDate that corresponds to the string parsed.
     *
     * @param date A string containing date information.
     * @return An instance of LocalDate that corresponds to the string parsed.
     * @throws InvalidInputException If the argument string does not follow the format.
     */
    public static LocalDate parseDate(String date) throws InvalidInputException {
        try {
            return LocalDate.parse(date, DATE_INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(MESSAGE_INVALID_DATE_FORMAT);
        }
    }

    /**
     * Returns an instance of {@link TaskList} that was stored in the file.
     *
     * @param file A file that contains a list of tasks.
     * @return A list of tasks that was stored in the file.
     * @throws FileNotFoundException If a file is not found.
     * @throws InvalidInputException If the date and time portion of the encoded task is not in the correct format.
     */
    public static TaskList parseFile(File file) throws FileNotFoundException, InvalidInputException {
        Scanner fileScanner = new Scanner(file);
        TaskList result = new TaskList();

        while (fileScanner.hasNext()) {
            Scanner lineScanner = new Scanner(fileScanner.nextLine());
            lineScanner.useDelimiter(",,,");
            String category = lineScanner.next();

            if (!lineScanner.hasNextInt()) {
                throw new InvalidStorageFileException();
            }
            boolean isDone = lineScanner.nextInt() == 1;
            if (!lineScanner.hasNext()) {
                throw new InvalidStorageFileException();
            }
            String description = lineScanner.next();

            if (category.equals("D") && lineScanner.hasNext()) {
                result.add(new Deadline(description, parseDateTime(lineScanner.next()), isDone));
            } else if (category.equals("E") && lineScanner.hasNext()) {
                result.add(new Event(description, parseDateTime(lineScanner.next()), isDone));
            } else if (category.equals("T")) {
                result.add(new ToDo(description, isDone));
            } else {
                throw new InvalidStorageFileException();
            }

            if (lineScanner.hasNext()) {
                throw new InvalidStorageFileException();
            }
        }
        return result;
    }
}
