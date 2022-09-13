package ted.parser;

import java.time.format.DateTimeParseException;

import ted.command.AddCommand;
import ted.command.ByeCommand;
import ted.command.Command;
import ted.command.DeleteCommand;
import ted.command.FindCommand;
import ted.command.ListCommand;
import ted.command.MarkCommand;
import ted.command.RemindCommand;
import ted.command.UnmarkCommand;
import ted.exception.TedException;
import ted.task.Deadline;
import ted.task.Event;
import ted.task.Todo;

/**
 * Represents a parser for decoding user commands. <code>Parser</code> calls the
 * correct <code>Command</code> according to the user input.
 */
public class Parser {
    private static final String NONCOMMAND_ERROR_MESSAGE = "Oh no, I don't understand T_T\n";
    private static final String MISSING_TASKNUMBER_ERROR_MESSAGE = "Oh no, please indicate task number T_T\n";
    private static final String NO_TASK_ERROR_MESSAGE = "Oh no, please include task description T_T\n";
    private static final String MISSING_DESCRIPTION_ERROR_MESSAGE = "Oh no, "
            + "please include both description and time T_T\n";
    private static final String DATE_FORMAT_ERROR_MESSAGE = "Oh no, "
            + "please input the date in yyyy-mm-dd hh:mm format T_T\n";
    private static final String NO_KEYWORD_ERROR_MESSAGE = "Oh no, enter a keyword to find tasks T_T\n";

    /**
     * Returns a Command object based on the parsed user input command.
     *
     * @param command user input command.
     * @return Command object to be executed by the bot.
     * @throws TedException if user input command does not exist.
     */
    public static Command parse(String command) throws TedException {
        Command c;
        String[] temp = command.split(" ", 2);

        switch (temp[0]) {
        case "bye":
        case "list":
        case "remind":
            c = parseSingleLengthCommand(temp);
            break;
        case "find":
            c = parseFindCommand(temp);
            break;
        case "mark":
        case "unmark":
        case "delete":
            c = parseNumberCommand(temp);
            break;
        case "todo":
            c = parseTodoCommand(temp);
            break;
        case "deadline":
            c = parseDeadlineCommand(temp);
            break;
        case "event":
            c = parseEventCommand(temp);
            break;
        default:
            throw new TedException(NONCOMMAND_ERROR_MESSAGE);
        }
        return c;
    }

    /**
     * Parses commands bye, list, and remind.
     *
     * @param fullCommand user input command
     * @return Command object to be executed by the bot.
     * @throws TedException if user input command is wrong.
     */
    private static Command parseSingleLengthCommand(String[] fullCommand) throws TedException {
        Command c;
        if (fullCommand.length != 1) {
            throw new TedException(NONCOMMAND_ERROR_MESSAGE);
        }

        switch (fullCommand[0]) {
        case "bye":
            c = new ByeCommand();
            break;
        case "list":
            c = new ListCommand();
            break;
        case "remind":
            c = new RemindCommand();
            break;
        default:
            throw new TedException(NONCOMMAND_ERROR_MESSAGE);
        }
        return c;
    }

    /**
     * Parses find command.
     *
     * @param fullCommand user input command.
     * @return FindCommand object to be executed by bot.
     * @throws TedException if user input command is wrong.
     */
    private static FindCommand parseFindCommand(String[] fullCommand) throws TedException {
        if (fullCommand.length < 2) {
            throw new TedException(NO_KEYWORD_ERROR_MESSAGE);
        }
        return new FindCommand(fullCommand[1]);
    }

    /**
     * Parses commands mark, unmark, and delete.
     *
     * @param fullCommand user input command.
     * @return Command object to be executed by the bot.
     * @throws TedException if user input command is wrong.
     */
    private static Command parseNumberCommand(String[] fullCommand) throws TedException {
        Command c;
        if (fullCommand.length == 1) {
            throw new TedException(MISSING_TASKNUMBER_ERROR_MESSAGE);
        }
        assert fullCommand.length > 1;

        try {
            switch (fullCommand[0]) {
            case "mark":
                c = new MarkCommand(Integer.parseInt(fullCommand[1]));
                break;
            case "unmark":
                c = new UnmarkCommand(Integer.parseInt(fullCommand[1]));
                break;
            case "delete":
                c = new DeleteCommand(Integer.parseInt(fullCommand[1]));
                break;
            default:
                throw new TedException(NONCOMMAND_ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            throw new TedException(MISSING_TASKNUMBER_ERROR_MESSAGE);
        }

        return c;
    }

    /**
     * Parses todo command.
     *
     * @param fullCommand user input command.
     * @return AddCommand object to be executed by the bot.
     * @throws TedException if user input command is wrong.
     */
    private static AddCommand parseTodoCommand(String[] fullCommand) throws TedException {
        if (fullCommand.length == 1) {
            throw new TedException(NO_TASK_ERROR_MESSAGE);
        }
        assert fullCommand.length > 1;

        return new AddCommand(new Todo(fullCommand[1]));
    }

    /**
     * Parses deadline command.
     *
     * @param fullCommand user input command.
     * @return AddCommand object to be executed by the bot.
     * @throws TedException if user input command is wrong.
     */
    private static AddCommand parseDeadlineCommand(String[] fullCommand) throws TedException {
        AddCommand c;
        try {
            if (fullCommand.length == 1) {
                throw new TedException(NO_TASK_ERROR_MESSAGE);
            }
            assert fullCommand.length > 1;

            String[] deadlineDesc = fullCommand[1].split(" /by ", 2);
            if (deadlineDesc.length == 1) {
                throw new TedException(MISSING_DESCRIPTION_ERROR_MESSAGE);
            }
            assert deadlineDesc.length > 1;

            return new AddCommand(new Deadline(deadlineDesc[0], deadlineDesc[1]));
        } catch (DateTimeParseException e) {
            throw new TedException(DATE_FORMAT_ERROR_MESSAGE);
        }
    }

    /**
     * Parses event command.
     *
     * @param fullCommand user input command.
     * @return AddCommand object to be executed by the bot.
     * @throws TedException if user input command is wrong.
     */
    private static AddCommand parseEventCommand(String[] fullCommand) throws TedException {
        AddCommand c;
        if (fullCommand.length == 1) {
            throw new TedException(NO_TASK_ERROR_MESSAGE);
        }
        assert fullCommand.length > 1;

        String[] eventDesc = fullCommand[1].split(" /at ", 2);
        if (eventDesc.length == 1) {
            throw new TedException(MISSING_DESCRIPTION_ERROR_MESSAGE);
        }
        assert eventDesc.length > 1;

        return new AddCommand(new Event(eventDesc[0], eventDesc[1]));
    }
}
