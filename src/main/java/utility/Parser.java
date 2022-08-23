package utility;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import command.*;
import exceptions.DukeException;
import task.Task;
import task.Deadline;
import task.Event;


/**
 * Handles all conversions required in the program.
 */
public class Parser {

    public static Command parse(String userInput) throws DukeException {
        userInput = userInput.trim();
        int firstWhiteSpaceIndex = userInput.indexOf(" ");
        String stringCommand = userInput.substring(0, firstWhiteSpaceIndex);
        if (stringCommand.equals("todo")) {
            return new AddTaskCommand();
        } else if (stringCommand.equals("event")) {
            return new AddEventCommand();
        }  else if (stringCommand.equals("deadline")) {
            return new AddDeadlineCommand();
        } else if (stringCommand.equals("delete")) {
            return new DeleteTaskCommand();
        } else if (stringCommand.equals("mark")) {
            return new MarkCommand();
        } else if (stringCommand.equals("unmark")) {
            return new UnmarkCommand();
        } else if (stringCommand.equals("istoday")) {
            return new CheckIsTodayCommand();
        }
        else if (stringCommand.equals("longdesc")) {
            return new GetLongDescriptionCommand();
        }
        throw new DukeException("Command invalid. /help for more information.");
    }

    public static Task stringToTask(String userInput) {
        userInput = userInput.trim();
        int firstWhiteSpaceIndex = userInput.indexOf(" ");
        String description = userInput.substring(firstWhiteSpaceIndex);
        return new Task(description);
    }

    public static Event stringToEvent(String userInput) throws DukeException {
        userInput = userInput.trim();
        int firstWhiteSpaceIndex = userInput.indexOf(" ");
        String description = userInput.substring(firstWhiteSpaceIndex);
        LocalDate date = getDate(userInput);
        return new Event(description, date);
    }

    public static Deadline stringToDeadline(String userInput) throws DukeException {
        userInput = userInput.trim();
        int firstWhiteSpaceIndex = userInput.indexOf(" ");
        String description = userInput.substring(firstWhiteSpaceIndex);
        LocalDate date = getDate(userInput);
        return new Deadline(description, date);
    }

    private static String getDescription(String input, String commandUsed) throws DukeException{
        String description;
        int startDescriptionIndex = input.indexOf(commandUsed) + commandUsed.length();
        int endDescriptionIndex = input.indexOf(" /");
        if (startDescriptionIndex < 0) {
            throw new DukeException("Command does not follow pattern <command> <description>...\n>>");
        } else {
            if (commandUsed.equals("event") || commandUsed.equals("deadline")) {
                if (endDescriptionIndex < 0) {
                    throw new DukeException("Command does not follow pattern  ... /<at/by> <date in DD-MM-YYYY>\n>>");
                } else {
                    description = input.substring(startDescriptionIndex, endDescriptionIndex).trim();
                }
            } else {
                description = input.substring(startDescriptionIndex).trim();
            }
        }
        if (description.equals("")) {
            throw new DukeException("Empty description field\n>>");
        }
        return description;
    }

    private static LocalDate getDate(String userInput) throws DukeException {
        try {
            String date = "";
            int startDateIndex = userInput.indexOf("/") + 3;
            if (startDateIndex < 0) {
                throw new DukeException("Command does not follow pattern ... /<at/by> <YYYY-MM-DD>\n>>");
            } else {
                date = userInput.substring(startDateIndex).trim();
                if (date.equals("")) {
                    throw new DukeException("Empty date field\n>>");
                }
                return LocalDate.parse(date);
            }
        } catch (DateTimeParseException dtpe) {
            throw new DukeException("Date is not valid\n>>");
        }
    }
}
