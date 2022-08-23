package roger;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import roger.commands.*;
import roger.exceptions.RogerInvalidInputException;

public class Parser {
    private final String LIST_COMMAND = "list";
    private final String MARK_COMMAND = "mark";
    private final String UNMARK_COMMAND = "unmark";
    private final String COMMAND_FIND = "find";
    private final String ADD_TODO_COMMAND = "todo";
    private final String ADD_DEADLINE_COMMAND = "deadline";
    private final String ADD_EVENT_COMMAND = "event";
    private final String DELETE_COMMAND = "delete";
    private final String EXIT_COMMAND = "bye";

    public Command parse(String input) throws RogerInvalidInputException {
        int cmdArgSeparator = input.indexOf(" ");
        String command = cmdArgSeparator < 0 ? input : input.substring(0, cmdArgSeparator);
        String arguments = cmdArgSeparator < 0 ? "" : input.substring(cmdArgSeparator + 1);

        switch (command) {
            case LIST_COMMAND:
                return parseListArguments(arguments);
            case MARK_COMMAND:
                return parseMarkArguments(arguments);
            case UNMARK_COMMAND:
                return parseUnmarkArguments(arguments);
            case COMMAND_FIND:
                return parseFindArguments(arguments);
            case ADD_TODO_COMMAND:
                return parseToDoArguments(arguments);
            case ADD_DEADLINE_COMMAND:
                return parseDeadlineArguments(arguments);
            case ADD_EVENT_COMMAND:
                return parseEventArguments(arguments);
            case DELETE_COMMAND:
                return parseDeleteArguments(arguments);
            case EXIT_COMMAND:
                return parseExitArguments(arguments);
            default:
                return parseUnknownCommandArguments(arguments);
        }
    }

    private ListCommand parseListArguments(String arguments) throws RogerInvalidInputException {
        String firstArg = arguments.split(" ")[0];

        if (firstArg.isBlank()) {
            return new ListCommand();
        }

        try {
            return new ListOnDateCommand(LocalDate.parse(firstArg));
        } catch (DateTimeParseException e) {
            throw new RogerInvalidInputException("List tasks with `list` or `list <yyyy-mm-dd>`");
        }
    }

    private MarkCommand parseMarkArguments(String arguments) throws RogerInvalidInputException {
        int taskNum;
        try {
            String firstArg = arguments.split(" ")[0];
            taskNum = Integer.parseInt(firstArg);
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            throw new RogerInvalidInputException("Mark tasks as done with `mark <task number>`");
        }

        return new MarkCommand(taskNum);
    }

    private UnmarkCommand parseUnmarkArguments(String arguments) throws RogerInvalidInputException {
        int taskNum;
        try {
            String firstArg = arguments.split(" ")[0];
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

        int dateKeywordIndex = arguments.indexOf("/by");
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

        int dateKeywordIndex = arguments.indexOf("/at");
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
            String firstArg = arguments.split(" ")[0];
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
