package duke;

import java.util.Arrays;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.PriorityCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;

/**
 * Parser parses and helps to make sense of user input.
 */
public class Parser {
    private enum Commands {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, PRIORITY
    }

    /**
     * Parses user input of length one and returns command to execute.
     *
     * @param commandToParse user command input.
     * @return command to execute.
     * @throws DukeException
     */
    public static Command parseInputOfLengthOne(Commands commandToParse) throws DukeException {
        switch (commandToParse) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case MARK:
        case UNMARK:
        case DELETE:
        case PRIORITY:
            throw new InvalidIndexException();
        case TODO:
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        case DEADLINE:
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        case EVENT:
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        case FIND:
            throw new DukeException("OOPS!!! The find command cannot be empty.");
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses user input greater than length one and returns command to execute.
     *
     * @param commandToParse user command input.
     * @param userInputs string array of user input delimited by whitespace.
     * @return command to execute
     * @throws DukeException
     */
    public static Command parseInputOfOtherLength(Commands commandToParse, String[] userInputs) throws DukeException {
        switch (commandToParse) {
        case MARK:
            return parseMark(userInputs);
        case UNMARK:
            return parseUnmark(userInputs);
        case TODO:
            return parseToDo(userInputs);
        case DEADLINE:
            return parseDeadline(userInputs);
        case EVENT:
            return parseEvent(userInputs);
        case FIND:
            return parseFind(userInputs);
        case PRIORITY:
            return parsePriority(userInputs);
        case DELETE:
            return parseDelete(userInputs);
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses mark command.
     *
     * @param userInputs string array of user input delimited by whitespace.
     * @return MarkCommand.
     * @throws DukeException
     */
    public static Command parseMark(String[] userInputs) throws DukeException {
        boolean isNotUserInputsLengthTwo = userInputs.length != 2;
        boolean isNotIndexOfTask = !userInputs[1].matches("\\d+");
        if (isNotUserInputsLengthTwo) {
            throw new InvalidCommandException();
        }
        if (isNotIndexOfTask) {
            throw new InvalidIndexException();
        }
        try {
            int indexToMark = Integer.parseInt(userInputs[1]);
            return new MarkCommand(indexToMark);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    /**
     * Parses unmark command.
     *
     * @param userInputs string array of user input delimited by whitespace.
     * @return UnmarkCommand.
     * @throws DukeException
     */
    public static Command parseUnmark(String[] userInputs) throws DukeException {
        boolean isNotUserInputsLengthTwo = userInputs.length != 2;
        boolean isNotIndexOfTask = !userInputs[1].matches("\\d+");
        if (isNotUserInputsLengthTwo) {
            throw new InvalidCommandException();
        }
        if (isNotIndexOfTask) {
            throw new InvalidIndexException();
        }
        try {
            int indexToUnmark = Integer.parseInt(userInputs[1]);
            return new UnmarkCommand(indexToUnmark);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    /**
     * Parses todo command.
     *
     * @param userInputs string array of user input delimited by whitespace.
     * @return ToDo command
     */
    public static Command parseToDo(String[] userInputs) {
        String[] toDoDescription = Arrays.copyOfRange(userInputs, 1, userInputs.length);
        return new ToDoCommand(String.join(" ", toDoDescription));
    }

    /**
     * Parses deadline command.
     *
     * @param userInputs string array of user input delimited by whitespace.
     * @return Deadline command.
     * @throws DukeException
     */
    public static Command parseDeadline(String[] userInputs) throws DukeException {
        boolean hasNoBy = !Arrays.asList(userInputs).contains("/by");
        if (hasNoBy) {
            throw new DukeException("OOPS!!! Please use the deadline command in the correct format.");
        }
        int byIndex = Arrays.asList(userInputs).indexOf("/by");
        String[] deadlineDescription = Arrays.copyOfRange(userInputs, 1, byIndex);
        String[] by = Arrays.copyOfRange(userInputs, byIndex + 1, userInputs.length);
        boolean hasNoValidDeadlineDate = !String.join(" ", by).matches("^\\d{4}-\\d{2}-\\d{2}$");
        if (hasNoValidDeadlineDate) {
            throw new InvalidDateException();
        }
        return new DeadlineCommand(String.join(" ", deadlineDescription),
                String.join(" ", by));
    }

    /**
     * Parses event command.
     *
     * @param userInputs string array of user input delimited by whitespace.
     * @return Event command.
     * @throws DukeException
     */
    public static Command parseEvent(String[] userInputs) throws DukeException {
        boolean hasNoAt = !Arrays.asList(userInputs).contains("/at");
        if (hasNoAt) {
            throw new DukeException("OOPS!!! Please use the event command in the correct format.");
        }
        int atIndex = Arrays.asList(userInputs).indexOf("/at");
        String[] eventDescription = Arrays.copyOfRange(userInputs, 1, atIndex);
        String[] at = Arrays.copyOfRange(userInputs, atIndex + 1, userInputs.length);
        boolean hasNoValidEventDate = !String.join(" ", at).matches("^\\d{4}-\\d{2}-\\d{2}$");
        if (hasNoValidEventDate) {
            throw new InvalidDateException();
        }
        return new EventCommand(String.join(" ", eventDescription),
                String.join(" ", at));
    }

    /**
     * Parses find command.
     *
     * @param userInputs string array of user input delimited by whitespace.
     * @return FindCommand
     */
    public static Command parseFind(String[] userInputs) {
        String[] searchKeywords = Arrays.copyOfRange(userInputs, 1, userInputs.length);
        return new FindCommand(String.join(" ", searchKeywords));
    }

    /**
     * Parses priority command.
     *
     * @param userInputs string array of user input delimited by whitespace.
     * @return PriorityCommand.
     * @throws DukeException
     */
    public static Command parsePriority(String[] userInputs) throws DukeException {
        boolean isNotUserInputsLengthThree = userInputs.length != 3;
        boolean isNotIndexOfTask = !userInputs[1].matches("\\d+");
        if (isNotUserInputsLengthThree) {
            throw new InvalidCommandException();
        }
        if (isNotIndexOfTask) {
            throw new InvalidIndexException();
        }
        boolean isNotLowPriority = !userInputs[2].trim().matches("\\blow\\b");
        boolean isNotMediumPriority = !userInputs[2].trim().matches("\\bmedium\\b");
        boolean isNotHighPriority = !userInputs[2].trim().matches("\\bhigh\\b");
        if (isNotLowPriority && isNotMediumPriority && isNotHighPriority) {
            throw new InvalidCommandException();
        }
        try {
            int indexToChangePriority = Integer.parseInt(userInputs[1]);
            String priority = userInputs[2].toUpperCase();
            return new PriorityCommand(indexToChangePriority, priority);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    /**
     * Parses delete command.
     *
     * @param userInputs string array of user input delimited by whitespace.
     * @return DeleteCommand.
     * @throws DukeException
     */
    public static Command parseDelete(String[] userInputs) throws DukeException {
        boolean isNotUserInputsLengthTwo = userInputs.length != 2;
        boolean isNotIndexOfTask = !userInputs[1].matches("\\d+");
        if (isNotUserInputsLengthTwo) {
            throw new InvalidCommandException();
        }
        if (isNotIndexOfTask) {
            throw new InvalidIndexException();
        }
        try {
            int indexToDelete = Integer.parseInt(userInputs[1]);
            return new DeleteCommand(indexToDelete);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    /**
     * Returns a command to carry out based on the user input.
     *
     * @param fullCommand user input.
     * @return Command to carry out.
     * @throws DukeException
     */
    public static Command parse(String fullCommand) throws DukeException {
        Commands command;
        String[] userInputs = fullCommand.trim().split(" ");
        boolean isUserInputsLengthOne = userInputs.length == 1;
        try {
            command = Commands.valueOf(userInputs[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }
        if (isUserInputsLengthOne) {
            Command commandToExecute = parseInputOfLengthOne(command);
            return commandToExecute;
        } else {
            Command commandToExecute = parseInputOfOtherLength(command, userInputs);
            return commandToExecute;
        }
    }
}

