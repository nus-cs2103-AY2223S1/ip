package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.MarkCommand;
import duke.command.TagCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static duke.exception.ErrorMessage.*;

/**
 * Represents duke chatbot parser that make sense of user commands.
 * If there is any invalid user input detected, the parser returns the error message (exception)
 * instead of the parsed command
 */
public class Parser {

    /**
     * Returns a LocalDate object parsed from a string containing date.
     * If the string date format is not accepted, exception is thrown.
     *
     * @param str a string containing a date.
     * @return LocalDate object.
     * @throws DukeException If the string date format is not accepted by duke chatbot.
     */
    public static LocalDate parseDate(String str) throws DukeException {
        // parse string format date to LocalDate object -> to String format yyyy-MM-dd, exception
        String time = str.trim();
        try {
            if (time.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
                return LocalDate.parse(time, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } else if (time.matches("([0-9]{4})/([0-9]{2})/([0-9]{2})")) {
                return LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            } else if (time.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
                return LocalDate.parse(time, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            } else if (time.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
                return LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } else if (time.matches("([0-9]{4}) ([0-9]{2}) ([0-9]{2})")) {
                return LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy MM dd"));
            } else if (time.matches("([0-9]{2}) ([0-9]{2}) ([0-9]{4})")) {
                return LocalDate.parse(time, DateTimeFormatter.ofPattern("dd MM yyyy"));
            } else {
                throw new DukeException(INVALID_DATE_FORMAT, "");
            }
        } catch (Exception e) {
            throw new DukeException(INVALID_DATE, "");
        }
    }


    /**
     * Checks the validity of user command type, throws DukeException if the command type
     * is invalid or required parameter is missing.
     *
     * @param taskString a String array containing a user command, the first element in the array is the command type
     * @throws DukeException If the command is regarded invalid/incomplete by duke chatbot.
     */
    public void parseCommandType(String[] taskString) throws duke.exception.DukeException {
        String type = taskString[0];
        if (taskString.length == 1 && (type.equals("todo") || type.equals("deadline") || type.equals("event"))) {
            throw new DukeException(MISSING_DESCRIPTION, type);
        } else if (taskString.length == 1 && (type.equals("mark") || type.equals("unmark") || type.equals("delete")
                || type.equals("tag") || type.equals("untag"))) {
            throw new DukeException(MISSING_INDEX, type);
        } else if (taskString.length == 1 && !type.equals("list") && !type.equals("bye") && !type.equals("help")) {
            throw new DukeException(INVALID_COMMAND_TYPE, type);
        }
    }


    /**
     * Returns simplified command string parsed from a user command.
     * If the command is invalid, exception is thrown.
     *
     * @param line a string containing a user command.
     * @param taskList a List of tasks.
     * @return command string understandable by other methods/classes.
     * @throws DukeException If the command is regarded invalid/incomplete by duke chatbot.
     */
    public String parseCommand(String line, TaskList taskList) throws DukeException {
        String[] taskString = line.split(" ", 2); //split by first white space
        parseCommandType(taskString);
        String taskType = taskString[0].trim();
        if (taskType.equals("todo")) {
            return new AddTodoCommand(taskList, taskString[1].trim()).execute();
        } else if (taskType.equals("deadline")) {
            return new AddDeadlineCommand(taskList, taskString[1].trim()).execute();
        } else if (taskType.equals("event")) {
            return new AddEventCommand(taskList, taskString[1].trim()).execute();
        } else if (taskType.equals("list")) {
            if (taskString.length == 1 || taskString[1].trim().equals("")) { //list all
                return "list 0";
            } else {
                return new ListCommand(taskList, taskString[1].trim()).execute();
            }
        } else if (taskType.equals("mark") || taskType.equals("unmark") || taskType.equals("delete")) {
            return new MarkCommand(taskList, taskType, taskString[1].trim()).execute();
        } else if (taskType.equals("tag") || taskType.equals("untag")) {
            return new TagCommand(taskList, taskType, taskString[1].trim()).execute();
        }else if (taskType.equals("bye") || taskType.equals("help")) {
            return taskType;
        } else {
            throw new DukeException(INVALID_COMMAND_TYPE, "");
        }
    }

}
