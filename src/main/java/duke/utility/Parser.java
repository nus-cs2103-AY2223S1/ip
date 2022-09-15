package duke.utility;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TimeTaskCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.command.FindCommand;
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
     * @throws DukeException  If fullCommand is empty or
     * invalid full command
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
        default:
            throw new DukeException(invalidAction(""));
        }
    }



    //newwwwwwwwwww
    public static String emptyMessage(String message) {
        if (message.equals("")) {
            return "The description cannot be empty";
        }
        return String.format("The description of %s cannot be empty", message);
    }

    public static String invalidAction(String message) {
        if (message.equals("")) {
            return "Invalid command, I don't know what that means :-(";
        }
        return String.format("It must be in the format of: %s <position in list>", message);
    }

    public static String invalidTaskAction(String message, String type) {
        return String.format("It must be in the format of: %s <desciption> /%s <yyyy-mm-dd HH:MM>", message, type);
    }

    public static String checkEmptyString(String[] split) throws DukeException {
        String keyword = split[0];
        if (keyword.equals("")) {
            throw new DukeException(emptyMessage(""));
        }
        return keyword;
    }

    public static Command parseBye(String[] split) throws DukeException {
        if (split.length > 1) {
            throw new DukeException(invalidAction(""));
        }
        return new ByeCommand();
    }

    public static Command parseList(String[] split) throws DukeException {
        if (split.length > 1) {
            throw new DukeException(invalidAction(""));
        }
        return new ListCommand();
    }

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

    public static Command parseTodo(String[] split) throws DukeException {
        if (split.length == 1) {
            throw new DukeException(emptyMessage("todo"));
        }
        return new TodoCommand(split[1]);
    }

    public static Command parseDeadline(String[] split) throws DukeException {
        if (split.length == 1) {
            throw new DukeException(emptyMessage("deadline"));
        }
        String input = split[1];

        int index = input.lastIndexOf("/by");

        if (index > -1) {
            String by = input.substring(index + 4, input.length());
            input = input.substring(1, index - 1);
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

    public static Command parseEvent(String[] split) throws DukeException {
        if (split.length == 1) {
            throw new DukeException(emptyMessage("event"));
        }

        String input = split[1];

        int index = input.lastIndexOf("/at");

        if (index > -1) {
            String at = input.substring(index + 4, input.length());
            input = input.substring(1, index - 1);
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

    public static Command parseFind(String[] split) throws DukeException {
        if (split.length == 1) {
            throw new DukeException(emptyMessage("find"));
        }
        return new FindCommand(split[1]);
    }





    //end
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
