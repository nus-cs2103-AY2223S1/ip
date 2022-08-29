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
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            if (taskList.isEmpty()) {
                throw new DukeException("OOPS!!! No tasks available!");
            } else {
                return new ListCommand();
            }
        } else if (fullCommand.length() >= 5 && (fullCommand.startsWith("mark")
                && (Character.isWhitespace(fullCommand.charAt(4)))
                && fullCommand.substring(5).chars().allMatch(Character::isDigit))) {
            int number = parseInt(fullCommand.substring(5));
            if (number > taskList.taskListSize() || number <= 0) {
                throw new DukeException("OOPS!!! Task number does not exist.");
            } else {
                return new MarkCommand(number);
            }
        } else if (fullCommand.length() >= 7 && (fullCommand.startsWith("unmark")
                && (Character.isWhitespace(fullCommand.charAt(6)))
                && fullCommand.substring(7).chars().allMatch(Character::isDigit))) {
            int number = parseInt(fullCommand.substring(7));
            if (number > taskList.taskListSize() || number <= 0) {
                throw new DukeException("OOPS!!! Task number does not exist.");
            } else {
                return new UnmarkCommand(number);
            }
        } else if (fullCommand.length() >= 7 && (fullCommand.startsWith("delete")
                && (Character.isWhitespace(fullCommand.charAt(6)))
                && fullCommand.substring(7).chars().allMatch(Character::isDigit))) {
            int number = parseInt(fullCommand.substring(7));
            if (number > taskList.taskListSize() || number <= 0) {
                throw new DukeException("OOPS!!! Task number does not exist.");
            } else {
                return new DeleteCommand(number);
            }
        } else if (fullCommand.equals("todo")
                || (fullCommand.startsWith("todo") && fullCommand.substring(5).isBlank())) {
        } else if (fullCommand.length() >= 5 && (fullCommand.startsWith("find")
                && (Character.isWhitespace(fullCommand.charAt(4))))) {
            String string = fullCommand.substring(5);
            if (string.isBlank()) {
                throw new DukeException("OOPS!!! Enter keyword to find.");
            } else {
                return new FindCommand(string);
            }
        } else if (fullCommand.equals("todo") || (fullCommand.startsWith("todo")
                && fullCommand.substring(5).isBlank())) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else if (fullCommand.startsWith("todo") && Character.isWhitespace(fullCommand.charAt(4))) {
            return new AddCommand(new ToDo(fullCommand));
        } else if (fullCommand.equals("deadline") || (fullCommand.startsWith("deadline")
                && fullCommand.substring(9).isBlank())) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (fullCommand.startsWith("deadline") && Character.isWhitespace(fullCommand.charAt(8))) {
            if (!fullCommand.contains("/by")) {
                throw new DukeException("OOPS!!! Please use the correct format!");
            } else {
                try {
                    return new AddCommand(new Deadline(fullCommand));
                } catch (DateTimeParseException e) {
                    throw new DukeException("OOPS!!! Please use the correct date format!");
                }
            }
        } else if (fullCommand.equals("event")
                || (fullCommand.startsWith("event") && fullCommand.substring(6).isBlank())) {
        } else if (fullCommand.equals("event") || (fullCommand.startsWith("event")
                && fullCommand.substring(6).isBlank())) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        } else if (fullCommand.startsWith("event") && Character.isWhitespace(fullCommand.charAt(5))) {
            if (!fullCommand.contains("/at")) {
                throw new DukeException("OOPS!!! Please use the correct format!");
            } else {
                try {
                    return new AddCommand(new Event(fullCommand));
                } catch (DateTimeParseException e) {
                    throw new DukeException("OOPS!!! Please use the correct date format!");
                }
            }
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return null;
    }
}
