package duke.parser;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SortCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.tasklist.TaskList;




/**
 * Parser class for parsing inputs from user
 */
public class Parser {

    /**
     * Turns date from MMM dd yyyy format to yyyy-MM-dd format
     *
     * @param string of a MMM dd yyyy format
     * @return string of a yyyy-MM-dd format
     */
    public static String parseLocalDate(String string) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate localdate = LocalDate.parse(string, format);
        return localdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Checks what the user types and calls whatever command that the user types corresponds with
     *
     * @param fullCommand input from user to be checked against available commands
     * @param taskList the taskList to be modified with tasks depending on command
     * @return a command that does different tasks depending on user input
     * @throws DukeException
     */
    public static Command parse(String fullCommand, TaskList taskList) throws DukeException {
        if (isValidByeCommand(fullCommand)) {
            return new ExitCommand();
        } else if (isValidListCommand(fullCommand)) {
            if (!taskList.isEmpty()) {
                return new ListCommand();
            } else {
                throw new DukeException("OOPS!!! No tasks available!");
            }
        } else if (isValidMarkCommand(fullCommand)) {
            if (isValidMarkNumber(fullCommand, taskList)) {
                return new MarkCommand(parseInt(fullCommand.substring(5)));
            } else {
                throw new DukeException("OOPS!!! Task number does not exist.");
            }
        } else if (isValidUnmarkCommand(fullCommand)) {
            if (isValidUnmarkNumber(fullCommand, taskList)) {
                return new UnmarkCommand(parseInt(fullCommand.substring(7)));
            } else {
                throw new DukeException("OOPS!!! Task number does not exist.");
            }
        } else if (isValidDeleteCommand(fullCommand)) {
            if (isValidDeleteNumber(fullCommand, taskList)) {
                return new DeleteCommand(parseInt(fullCommand.substring(7)));
            } else {
                throw new DukeException("OOPS!!! Task number does not exist.");
            }
        } else if (isValidSortCommand(fullCommand)) {
            return new SortCommand();
        } else if (isValidFindCommand(fullCommand)) {
            if (isNotBlankFind(fullCommand)) {
                return new FindCommand(fullCommand.substring(5));
            } else {
                throw new DukeException("OOPS!!! Enter keyword to find.");
            }
        } else if (isEmptyTodoCommand(fullCommand)) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else if (isValidTodoCommand(fullCommand)) {
            return new AddCommand(new ToDo(fullCommand));
        } else if (isEmptyDeadlineCommand(fullCommand)) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (isValidDeadlineCommand(fullCommand)) {
            if (hasBy(fullCommand)) {
                try {
                    return new AddCommand(new Deadline(fullCommand));
                } catch (DateTimeParseException e) {
                    throw new DukeException("OOPS!!! Please use the correct date format!");
                }
            } else {
                throw new DukeException("OOPS!!! Please use the correct format!");
            }
        } else if (isEmptyEventCommand(fullCommand)) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        } else if (isValidEventCommand(fullCommand)) {
            if (hasAt(fullCommand)) {
                try {
                    return new AddCommand(new Event(fullCommand));
                } catch (DateTimeParseException e) {
                    throw new DukeException("OOPS!!! Please use the correct date format!");
                }
            } else {
                throw new DukeException("OOPS!!! Please use the correct format!");
            }
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Checks if the number after mark is valid
     * @param fullCommand the command entered by user
     * @param taskList the tasklist that holds the tasks
     * @return true if the number is valid, false if otherwise
     */
    public static boolean isValidMarkNumber(String fullCommand, TaskList taskList) {
        int number = parseInt(fullCommand.substring(5));
        if (number > taskList.getTaskListSize() || number <= 0) {
            return false;
        } else {
            assert number > 0 : "Integer is not valid!";
            return true;
        }
    }

    /**
     * Checks if the number after unmark is valid
     * @param fullCommand the command entered by user
     * @param taskList the tasklist that holds the tasks
     * @return true if the number is valid, false if otherwise
     */
    public static boolean isValidUnmarkNumber(String fullCommand, TaskList taskList) {
        int number = parseInt(fullCommand.substring(7));
        if (number > taskList.getTaskListSize() || number <= 0) {
            return false;
        } else {
            assert number > 0 : "Integer is not valid!";
            return true;
        }
    }

    /**
     * Checks if the number after delete is valid
     * @param fullCommand the command entered by user
     * @param taskList the tasklist that holds the tasks
     * @return true if the number is valid, false if otherwise
     */
    public static boolean isValidDeleteNumber(String fullCommand, TaskList taskList) {
        int number = parseInt(fullCommand.substring(7));
        if (number > taskList.getTaskListSize() || number <= 0) {
            return false;
        } else {
            assert number > 0 : "Integer is not valid!";
            return true;
        }
    }

    /**
     * Checks if the string after find is not blank
     * @param fullCommand the command entered by user
     * @return true if string after find is not blank
     */
    public static boolean isNotBlankFind(String fullCommand) {
        String string = fullCommand.substring(5);
        if (string.isBlank()) {
            return false;
        } else {
            assert string != null;
            return true;
        }
    }

    /**
     * Checks if the string contains /by
     * @param fullCommand the command entered by user
     * @return true if string contains /by
     */
    public static boolean hasBy(String fullCommand) {
        return fullCommand.contains("/by");
    }

    /**
     * Checks if the string contains /at
     * @param fullCommand the command entered by user
     * @return true if string contains /at
     */
    public static boolean hasAt(String fullCommand) {
        return fullCommand.contains("/at");
    }

    /**
     * Checks if the mark command is valid
     * @param fullCommand the command entered by user
     * @return true if the mark command is valid
     */
    public static boolean isValidMarkCommand(String fullCommand) {
        return fullCommand.length() >= 5 && (fullCommand.startsWith("mark")
                && (Character.isWhitespace(fullCommand.charAt(4)))
                && fullCommand.substring(5).chars().allMatch(Character::isDigit));
    }

    /**
     * Checks if the bye command is valid
     * @param fullCommand the command entered by user
     * @return true if the bye command is valid
     */
    public static boolean isValidByeCommand(String fullCommand) {
        return fullCommand.equals("bye");
    }

    /**
     * Checks if the list command is valid
     * @param fullCommand the command entered by user
     * @return true if the list command is valid
     */
    public static boolean isValidListCommand(String fullCommand) {
        return fullCommand.equals("list");
    }

    /**
     * Checks if the unmark command is valid
     * @param fullCommand the command entered by user
     * @return true if the unmark command is valid
     */
    public static boolean isValidUnmarkCommand(String fullCommand) {
        return fullCommand.length() >= 7 && (fullCommand.startsWith("unmark")
                && (Character.isWhitespace(fullCommand.charAt(6)))
                && fullCommand.substring(7).chars().allMatch(Character::isDigit));
    }

    /**
     * Checks if the delete command is valid
     * @param fullCommand the command entered by user
     * @return true if the delete command is valid
     */
    public static boolean isValidDeleteCommand(String fullCommand) {
        return fullCommand.length() >= 7 && (fullCommand.startsWith("delete")
                && (Character.isWhitespace(fullCommand.charAt(6)))
                && fullCommand.substring(7).chars().allMatch(Character::isDigit));
    }

    /**
     * Checks if the sort command is valid
     * @param fullCommand the command entered by user
     * @return true if the sort command is valid
     */
    public static boolean isValidSortCommand(String fullCommand) {
        return fullCommand.equals("sort");
    }

    /**
     * Checks if the find command is valid
     * @param fullCommand the command entered by user
     * @return true if the find command is valid
     */
    public static boolean isValidFindCommand(String fullCommand) {
        return fullCommand.length() >= 5 && (fullCommand.startsWith("find")
                && (Character.isWhitespace(fullCommand.charAt(4))));
    }

    /**
     * Checks if the todo command is empty
     * @param fullCommand the command entered by user
     * @return true if the todo command is empty
     */
    public static boolean isEmptyTodoCommand(String fullCommand) {
        return fullCommand.equals("todo") || (fullCommand.startsWith("todo")
                && fullCommand.substring(5).isBlank());
    }

    /**
     * Checks if the todo command is valid
     * @param fullCommand the command entered by user
     * @return true if the todo command is valid
     */
    public static boolean isValidTodoCommand(String fullCommand) {
        return fullCommand.startsWith("todo") && Character.isWhitespace(fullCommand.charAt(4));
    }

    /**
     * Checks if the deadline command is empty
     * @param fullCommand the command entered by user
     * @return true if the deadline command is empty
     */
    public static boolean isEmptyDeadlineCommand(String fullCommand) {
        return fullCommand.equals("deadline") || (fullCommand.startsWith("deadline")
                && fullCommand.substring(9).isBlank());
    }

    /**
     * Checks if the deadline command is valid
     * @param fullCommand the command entered by user
     * @return true if the deadline command is valid
     */
    public static boolean isValidDeadlineCommand(String fullCommand) {
        return fullCommand.startsWith("deadline") && Character.isWhitespace(fullCommand.charAt(8));
    }

    /**
     * Checks if the event command is empty
     * @param fullCommand the command entered by user
     * @return true if the event command is empty
     */
    public static boolean isEmptyEventCommand(String fullCommand) {
        return fullCommand.equals("event")
                || (fullCommand.startsWith("event") && fullCommand.substring(6).isBlank());
    }

    /**
     * Checks if the event command is valid
     * @param fullCommand the command entered by user
     * @return true if the event command is valid
     */
    public static boolean isValidEventCommand(String fullCommand) {
        return fullCommand.startsWith("event") && Character.isWhitespace(fullCommand.charAt(5));
    }
}



