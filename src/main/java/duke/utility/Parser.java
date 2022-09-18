package duke.utility;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.*;
import duke.exception.DukeException;

/**
 * Represents parser for duke
 */
public class Parser {
    /**
     * Parses the string command and returns the
     * correct command for it
     *
     * @param fullCommand full command given by user
     * @return the correct Command for the given keyword
     * @throws DukeException  If fullCommand is empty or invalid full command
     */
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand == null || fullCommand.equals("")) {
            throw new DukeException(emptyMessage(""));
        }
        String[] split =  fullCommand.split(" ", 2);
        String keyword = checkEmptyString(split);
        switch (keyword) {
        case "bye":
            return parseBye(split);
        case "list":
            return parseList(split);
        case "mark":
            return parseMark(split, fullCommand);
        case "unmark":
            return parseUnmark(split, fullCommand);
        case "delete":
            return parseDelete(split, fullCommand);
        case "todo":
            return parseTodo(split);
        case "deadline":
            return parseDeadline(split);
        case "event":
            return parseEvent(split);
        case "find":
            return parseFind(split);
        case "help":
            return parseHelp(split);
        default:
            throw new DukeException(invalidAction(""));
        }
    }

    /**
     * Returns a String representation of error message to be thrown for DukeException
     * for empty descriptions.
     *
     * @param message String representation of command that its description is empty
     * @return String representation error message to be thrown for DukeException
     */
    public static String emptyMessage(String message) {
        if (message.equals("")) {
            return "The description cannot be empty";
        }
        return String.format("The description of %s cannot be empty", message);
    }

    /**
     * Returns a String representation of error message to be thrown for DukeException
     * that is an invalid command.
     *
     * @param message String representation of command that is given by user and has invalid format
     * @return String representation error message to be thrown for DukeException
     */
    public static String invalidAction(String message) {
        if (message.equals("")) {
            return "Invalid command, I don't know what that means :-(";
        }
        return String.format("It must be in the format of: %s <position in list>", message);
    }


    /**
     * Returns a String representation of error message to be thrown for DukeException
     * that is an invalid command for tasks that has time element.
     *
     * @param message String representation of a command with time element
     * that is given by user and has invalid format
     * @param type String format of whether it is at or by
     * @return String representation error message to be thrown for DukeException
     */
    public static String invalidTaskAction(String message, String type) {
        return String.format("It must be in the format of: %s <description> /%s <yyyy-mm-dd HH:MM>", message, type);
    }

    /**
     * Checks for empty command and return the keyword of the full command.
     *
     * @param split fullCommand given by user that is split by spaces
     * @return Returns the keyword of the full command.
     * @throws DukeException If split[0] is empty string.
     */
    public static String checkEmptyString(String[] split) throws DukeException {
        String keyword = split[0];
        if (keyword.equals("")) {
            throw new DukeException(emptyMessage(""));
        }
        return keyword;
    }

    /**
     * Return a ByeCommand
     *
     * @param split fullCommand given by user that is split by spaces
     * @return Returns ByeCommand
     * @throws DukeException If split.length > 1.
     */
    public static Command parseBye(String[] split) throws DukeException {
        if (split.length > 1) {
            throw new DukeException(invalidAction(""));
        }
        return new ByeCommand();
    }

    /**
     * Return a HelpCommand
     *
     * @param split fullCommand given by user that is split by spaces
     * @return Returns HelpCommand
     * @throws DukeException If split.length > 1.
     */
    public static Command parseHelp(String[] split) throws DukeException {
        if (split.length > 1) {
            throw new DukeException(invalidAction(""));
        }
        return new HelpCommand();
    }


    /**
     * Return a ListCommand
     *
     * @param split fullCommand given by user that is split by spaces
     * @return Returns ListCommand
     * @throws DukeException If split.length > 1.
     */
    public static Command parseList(String[] split) throws DukeException {
        if (split.length > 1) {
            throw new DukeException(invalidAction(""));
        }
        return new ListCommand();
    }

    /**
     * Return a MarkCommand
     *
     * @param split fullCommand given by user that is split by spaces
     * @param fullCommand full command input by user
     * @return Returns MarkCommand
     * @throws DukeException If split.length == 1. If fullCommand has more than 1 word and 1 index
     * or index is not a number
     */
    public static Command parseMark(String[] split, String fullCommand) throws DukeException {
        if (split.length == 1) {
            throw new DukeException(emptyMessage("mark"));
        }

        split = fullCommand.split(" ");
        if (split.length > 2) {
            throw new DukeException(invalidAction("mark"));
        } else {
            boolean isNumeric = split[1].chars().allMatch(Character::isDigit);
            if (!isNumeric) {
                throw new DukeException(invalidAction("mark"));
            }
        }
        return new MarkCommand(Integer.parseInt(split[1]));
    }

    /**
     * Return a UnmarkCommand
     *
     * @param split fullCommand given by user that is split by spaces
     * @param fullCommand full command input by user
     * @return Returns UnmarkCommand
     * @throws DukeException If split.length == 1. If fullCommand has more than 1 word and 1 index
     * or index is not a number
     */
    public static Command parseUnmark(String[] split, String fullCommand) throws DukeException {
        if (split.length == 1) {
            throw new DukeException(emptyMessage("unmark"));
        }

        split = fullCommand.split(" ");
        if (split.length > 2) {
            throw new DukeException(invalidAction("unmark"));
        } else {
            boolean isNumeric = split[1].chars().allMatch(Character::isDigit);
            if (!isNumeric) {
                throw new DukeException(invalidAction("unmark"));
            }
        }
        return new UnmarkCommand(Integer.parseInt(split[1]));
    }

    /**
     * Return a DeleteCommand
     *
     * @param split fullCommand given by user that is split by spaces
     * @param fullCommand full command input by user
     * @return Returns DeleteCommand
     * @throws DukeException If split.length == 1. If fullCommand has more than 1 word and 1 index
     * or index is not a number
     */
    public static Command parseDelete(String[] split, String fullCommand) throws DukeException {
        if (split.length == 1) {
            throw new DukeException(emptyMessage("delete"));
        }

        split = fullCommand.split(" ");
        if (split.length > 2) {
            throw new DukeException(invalidAction("delete"));
        } else {
            boolean isNumeric = split[1].chars().allMatch(Character::isDigit);
            if (!isNumeric) {
                throw new DukeException(invalidAction("delete"));
            }
        }
        return new DeleteCommand(Integer.parseInt(split[1]));
    }

    /**
     * Return a TodoCommand
     *
     * @param split fullCommand given by user that is split by spaces
     * @return Returns TodoCommand
     * @throws DukeException If split.length == 1. If split[1].isBlank() == true.
     */
    public static Command parseTodo(String[] split) throws DukeException {
        if (split.length == 1) {
            throw new DukeException(emptyMessage("todo"));
        }
        if (split[1].isBlank()) {
            throw new DukeException(emptyMessage("todo"));
        }
        return new TodoCommand(split[1]);
    }

    /**
     * Return a TimeTaskCommand with deadline keyword
     *
     * @param split fullCommand given by user that is split by spaces
     * @return Return a TimeTaskCommand with deadline keyword
     * @throws DukeException If split.length == 1. Or
     * If format of user input is not deadline <description> /by yyyy-MM-dd HH:mm
     */
    public static Command parseDeadline(String[] split) throws DukeException {
        if (split.length == 1) {
            throw new DukeException(emptyMessage("deadline"));
        }
        String input = split[1];
        int index = input.lastIndexOf("/by");
        if (index > -1) {
            String by = input.substring(index + 4);
            if (index - 1 <= 0) {
                throw new DukeException(invalidTaskAction("deadline", "by"));
            }
            if (input.charAt(index - 1) != ' ') {
                throw new DukeException(invalidTaskAction("deadline", "by"));
            }
            String check = input.substring(0, index);
            if (check.isBlank()) {
                throw new DukeException(emptyMessage("deadline"));
            }
            input = input.substring(0, index - 1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            boolean validTime = isValid(by, formatter);
            if (!validTime) {
                throw new DukeException(invalidTaskAction("deadline", "by"));
            }
            return new TimeTaskCommand(input, "deadline", LocalDateTime.parse(by, formatter));
        } else {
            throw new DukeException(invalidTaskAction("deadline", "by"));
        }
    }

    /**
     * Return a TimeTaskCommand with event keyword
     *
     * @param split fullCommand given by user that is split by spaces
     * @return Return a TimeTaskCommand with event keyword
     * @throws DukeException If split.length == 1. Or
     * If format of user input is not event <description> /at yyyy-MM-dd HH:mm
     */
    public static Command parseEvent(String[] split) throws DukeException {
        if (split.length == 1) {
            throw new DukeException(emptyMessage("event"));
        }
        String input = split[1];
        int index = input.lastIndexOf("/at");
        if (index > -1) {
            String at = input.substring(index + 4);
            if (index - 1 <= 0) {
                throw new DukeException(invalidTaskAction("event", "at"));
            }
            if (input.charAt(index - 1) != ' ') {
                throw new DukeException(invalidTaskAction("event", "at"));
            }
            String check = input.substring(0, index);
            if (check.isBlank()) {
                throw new DukeException(emptyMessage("event"));
            }
            input = input.substring(0, index - 1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            boolean validTime = isValid(at, formatter);
            if (!validTime) {
                throw new DukeException(invalidTaskAction("event", "at"));
            }
            return new TimeTaskCommand(input, "event", LocalDateTime.parse(at, formatter));
        } else {
            throw new DukeException(invalidTaskAction("event", "at"));
        }
    }

    /**
     * Return a FindCommand
     *
     * @param split fullCommand given by user that is split by spaces
     * @return Returns FindCommand
     * @throws DukeException If split.length == 1.
     */
    public static Command parseFind(String[] split) throws DukeException {
        if (split.length == 1) {
            throw new DukeException(emptyMessage("find"));
        }
        return new FindCommand(split[1]);
    }

    /**
     * Checks if the string representation of time is
     * in the correct format
     *
     * @param str Time we want to check
     * @param formatter format we require
     * @return boolean where the string is valid
     */
    public static boolean isValid(String str, DateTimeFormatter formatter) {
        try {
            LocalDateTime date = LocalDateTime.parse(str, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

}
