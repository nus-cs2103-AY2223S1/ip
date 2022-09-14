package rattus.chatbot.util;

import static rattus.chatbot.common.DateFormat.DATE_INPUT_FORMAT;
import static rattus.chatbot.common.DateFormat.DATE_TIME_INPUT_FORMAT;
import static rattus.chatbot.common.Message.MESSAGE_INVALID_DATE_FORMAT;
import static rattus.chatbot.common.Message.MESSAGE_INVALID_DATE_TIME_FORMAT;
import static rattus.chatbot.common.Message.MESSAGE_TOO_MANY_ARGUMENTS;
import static rattus.chatbot.common.Message.MESSAGE_UNEXPECTED;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rattus.chatbot.command.Command;
import rattus.chatbot.command.ExitCommand;
import rattus.chatbot.command.ListCommand;
import rattus.chatbot.command.addcommands.AddDeadlineCommand;
import rattus.chatbot.command.addcommands.AddEventCommand;
import rattus.chatbot.command.addcommands.AddToDoCommand;
import rattus.chatbot.command.filecommands.ChangeFileCommand;
import rattus.chatbot.command.filecommands.DeleteFileCommand;
import rattus.chatbot.command.filecommands.NewFileCommand;
import rattus.chatbot.command.filtercommands.CheckDateCommand;
import rattus.chatbot.command.filtercommands.FindKeywordCommand;
import rattus.chatbot.command.taskcommands.DeleteCommand;
import rattus.chatbot.command.taskcommands.MarkCommand;
import rattus.chatbot.command.taskcommands.UnmarkCommand;
import rattus.chatbot.data.exception.InvalidInputException;
import rattus.chatbot.data.task.TaskList;

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
        String commandWord = getCommandWord(userInput);
        String arguments = getArguments(userInput);

        switch (commandWord) {
        // Add task commands
        case AddToDoCommand.COMMAND_WORD:
            return new AddToDoCommand(arguments);
        case AddDeadlineCommand.COMMAND_WORD:
            return new AddDeadlineCommand(arguments);
        case AddEventCommand.COMMAND_WORD:
            return new AddEventCommand(arguments);

        // File commands
        case NewFileCommand.COMMAND_WORD:
            return new NewFileCommand(arguments);
        case DeleteFileCommand.COMMAND_WORD:
            return new DeleteFileCommand(arguments);
        case ChangeFileCommand.COMMAND_WORD:
            return new ChangeFileCommand(arguments);

        // Filter query commands
        case FindKeywordCommand.COMMAND_WORD:
            return new FindKeywordCommand(arguments);
        case CheckDateCommand.COMMAND_WORD:
            return new CheckDateCommand(arguments);

        // Targeted task commands
        case MarkCommand.COMMAND_WORD:
            return new MarkCommand(arguments);
        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommand(arguments);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(arguments);

        // No argument commands
        case ListCommand.COMMAND_WORD:
            exceptionIfNotEmpty(arguments);
            return new ListCommand();
        case ExitCommand.COMMAND_WORD:
            exceptionIfNotEmpty(arguments);
            return new ExitCommand();

        default:
            throw new InvalidInputException(MESSAGE_UNEXPECTED);
        }
    }

    /**
     * Parses the command word from the user input string.
     *
     * @param userInput The user input string.
     * @return The command word in string form.
     * @throws InvalidInputException If the user input is in the wrong format.
     */
    private static String getCommandWord(String userInput) throws InvalidInputException {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            throw new InvalidInputException(MESSAGE_UNEXPECTED);
        }
        return matcher.group("commandWord").strip();
    }

    /**
     * Parses the arguments from the user input string.
     *
     * @param userInput The user input string.
     * @return The arguments in string form.
     * @throws InvalidInputException If the user input is in the wrong format.
     */
    private static String getArguments(String userInput) throws InvalidInputException {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            throw new InvalidInputException(MESSAGE_UNEXPECTED);
        }
        return matcher.group("arguments").strip();
    }

    /**
     * Throws an exception if the argument is not empty.
     *
     * @param arguments The string of arguments parsed.
     * @throws InvalidInputException If there is an argument.
     */
    private static void exceptionIfNotEmpty(String arguments) throws InvalidInputException {
        if (!arguments.isEmpty()) {
            throw new InvalidInputException(MESSAGE_TOO_MANY_ARGUMENTS);
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
     * @throws FileNotFoundException If a file is not found or is in the wrong format.
     */
    public static TaskList parseFile(File file) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);
        TaskList result = new TaskList();
        while (fileScanner.hasNext()) {
            result.add(TaskDecoder.decode(fileScanner.nextLine()));
        }
        return result;
    }
}
