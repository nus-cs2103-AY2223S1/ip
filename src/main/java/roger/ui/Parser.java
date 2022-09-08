package roger.ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import roger.commands.AddDeadlineCommand;
import roger.commands.AddEventCommand;
import roger.commands.AddToDoCommand;
import roger.commands.Command;
import roger.commands.DeleteTaskCommand;
import roger.commands.ExitCommand;
import roger.commands.FindCommand;
import roger.commands.ListCommand;
import roger.commands.ListOnDateCommand;
import roger.commands.MarkCommand;
import roger.commands.ScheduleCommand;
import roger.commands.UnknownCommand;
import roger.commands.UnmarkCommand;
import roger.exceptions.RogerInvalidInputException;

/**
 * Encapsulates logic for parsing user input.
 */
public class Parser {
    private final String COMMAND_LIST = "list";
    private final String COMMAND_MARK = "mark";
    private final String COMMAND_UNMARK = "unmark";
    private final String COMMAND_FIND = "find";
    private final String COMMAND_ADD_TODO = "todo";
    private final String COMMAND_ADD_DEADLINE = "deadline";
    private final String COMMAND_ADD_EVENT = "event";
    private final String COMMAND_DELETE = "delete";
    private final String COMMAND_EXIT = "bye";
    private final String COMMAND_SCHEDULE = "schedule";

    private final String CMD_ARG_SEPARATOR_STRING = " ";

    private final String KEYWORD_DEADLINE_DATE = "/by";
    private final String KEYWORD_EVENT_DATE = "/at";

    /**
     * Parses user input and return the corresponding command.
     *
     * @param input User input.
     * @return The command corresponding to the user input.
     * @throws RogerInvalidInputException If the command is known but the argument format is incorrect.
     */
    public Command parse(String input) throws RogerInvalidInputException {
        int cmdArgSeparator = input.indexOf(CMD_ARG_SEPARATOR_STRING);
        boolean argsAreProvided = cmdArgSeparator >= 0;
        String command = argsAreProvided ? input.substring(0, cmdArgSeparator) : input;
        String arguments = argsAreProvided ? input.substring(cmdArgSeparator + 1) : "";

        switch (command) {
        case COMMAND_LIST:
            return parseListArguments(arguments);
        case COMMAND_SCHEDULE:
            return parseScheduleArguments(arguments);
        case COMMAND_MARK:
            return parseMarkArguments(arguments);
        case COMMAND_UNMARK:
            return parseUnmarkArguments(arguments);
        case COMMAND_FIND:
            return parseFindArguments(arguments);
        case COMMAND_ADD_TODO:
            return parseToDoArguments(arguments);
        case COMMAND_ADD_DEADLINE:
            return parseDeadlineArguments(arguments);
        case COMMAND_ADD_EVENT:
            return parseEventArguments(arguments);
        case COMMAND_DELETE:
            return parseDeleteArguments(arguments);
        case COMMAND_EXIT:
            return parseExitArguments(arguments);
        default:
            return parseUnknownCommandArguments(arguments);
        }
    }

    private ListCommand parseListArguments(String arguments) throws RogerInvalidInputException {
        String firstArg = arguments.split(CMD_ARG_SEPARATOR_STRING)[0];

        if (firstArg.isBlank()) {
            return new ListCommand();
        }

        try {
            return new ListOnDateCommand(LocalDate.parse(firstArg));
        } catch (DateTimeParseException e) {
            throw new RogerInvalidInputException("List tasks with `list` or `list <yyyy-mm-dd>`");
        }
    }

    private ScheduleCommand parseScheduleArguments(String arguments) throws RogerInvalidInputException {
        String[] argList = arguments.split(CMD_ARG_SEPARATOR_STRING);
        if (argList.length < 2) {
            throw new RogerInvalidInputException("See your schedule with `schedule <yyyy-mm-dd> <yyyy-mm-dd>`");
        }

        String firstArg = argList[0];
        String secondArg = argList[1];
        if (firstArg.isBlank() || secondArg.isBlank()) {
            throw new RogerInvalidInputException("See your schedule with `schedule <yyyy-mm-dd> <yyyy-mm-dd>`");
        }

        try {
            return new ScheduleCommand(LocalDate.parse(firstArg), LocalDate.parse(secondArg));
        } catch (DateTimeParseException e) {
            throw new RogerInvalidInputException("See your schedule with `schedule <yyyy-mm-dd> <yyyy-mm-dd>`");
        }
    }

    private MarkCommand parseMarkArguments(String arguments) throws RogerInvalidInputException {
        int taskNum;
        try {
            String firstArg = arguments.split(CMD_ARG_SEPARATOR_STRING)[0];
            taskNum = Integer.parseInt(firstArg);
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            throw new RogerInvalidInputException("Mark tasks as done with `mark <task number>`");
        }

        return new MarkCommand(taskNum);
    }

    private UnmarkCommand parseUnmarkArguments(String arguments) throws RogerInvalidInputException {
        int taskNum;
        try {
            String firstArg = arguments.split(CMD_ARG_SEPARATOR_STRING)[0];
            taskNum = Integer.parseInt(firstArg);
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            throw new RogerInvalidInputException("Unmark tasks as done with `mark <task number>`");
        }

        return new UnmarkCommand(taskNum);
    }

    private FindCommand parseFindArguments(String arguments) throws RogerInvalidInputException {
        String query = arguments.trim();
        if (query.isBlank()) {
            throw new RogerInvalidInputException("Where your search term?");
        }
        return new FindCommand(query);
    }

    private AddToDoCommand parseToDoArguments(String arguments) throws RogerInvalidInputException {
        String taskName = arguments.trim();
        if (taskName.isBlank()) {
            throw new RogerInvalidInputException("Add to-do with `todo <name>`");
        }
        return new AddToDoCommand(taskName);
    }

    private AddDeadlineCommand parseDeadlineArguments(String arguments) throws RogerInvalidInputException {
        String taskName;
        LocalDate date;

        int dateKeywordIndex = arguments.indexOf(KEYWORD_DEADLINE_DATE);
        if (dateKeywordIndex < 0) {
            throw new RogerInvalidInputException("Add deadlines with `deadline <name> /by <yyyy-mm-dd>`");
        }

        try {
            taskName = arguments.substring(0, dateKeywordIndex - 1);
            date = LocalDate.parse(arguments.substring(dateKeywordIndex + 4));
        } catch (AssertionError | ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new RogerInvalidInputException("Add deadlines with `deadline <name> /by <yyyy-mm-dd>`");
        }

        return new AddDeadlineCommand(taskName, date);
    }

    private AddEventCommand parseEventArguments(String arguments) throws RogerInvalidInputException {
        String taskName;
        LocalDate date;

        int dateKeywordIndex = arguments.indexOf(KEYWORD_EVENT_DATE);
        if (dateKeywordIndex < 0) {
            throw new RogerInvalidInputException("Add events with `event <name> /at <yyyy-mm-dd>`");
        }

        try {
            taskName = arguments.substring(0, dateKeywordIndex - 1);
            date = LocalDate.parse(arguments.substring(dateKeywordIndex + 4));
        } catch (AssertionError | ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new RogerInvalidInputException("Add events with `event <name> /at <yyyy-mm-dd>`");
        }

        return new AddEventCommand(taskName, date);
    }

    private DeleteTaskCommand parseDeleteArguments(String arguments) throws RogerInvalidInputException {
        int taskName;

        try {
            String firstArg = arguments.split(CMD_ARG_SEPARATOR_STRING)[0];
            taskName = Integer.parseInt(firstArg);
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            throw new RogerInvalidInputException("Delete tasks with `delete <task number>`");
        }

        return new DeleteTaskCommand(taskName);
    }

    private ExitCommand parseExitArguments(String arguments) {
        return new ExitCommand();
    }

    private UnknownCommand parseUnknownCommandArguments(String arguments) {
        return new UnknownCommand();
    }
}
