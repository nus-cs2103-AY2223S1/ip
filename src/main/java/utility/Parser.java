package utility;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import command.AddEventCommand;
import command.AddDeadlineCommand;
import command.AddTaskCommand;
import command.FindCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import command.ListCommand;
import command.CheckIsTodayCommand;
import command.ExitCommand;
import command.GetLongDescriptionCommand;
import command.HelpCommand;
import command.Command;
import command.DeleteTaskCommand;
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
        String stringCommand = "";
        if (firstWhiteSpaceIndex < 0) {
             stringCommand = userInput;
        } else {
             stringCommand = userInput.substring(0, firstWhiteSpaceIndex);
        }
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
        else if (stringCommand.equals("list")) {
            return new ListCommand();
        }
        else if (stringCommand.equals("bye")) {
            return new ExitCommand();
        } else if (stringCommand.equals("help")) {
            return new HelpCommand();
        } else if (stringCommand.equals("find")) {
            return new FindCommand();
        }
        throw new DukeException("Command invalid. Type help for more information.");
    }

    public static Task stringToTask(String userInput) throws DukeException{
        String description = getDescription("todo", userInput);
        return new Task(description);
    }

    public static Event stringToEvent(String userInput) throws DukeException {
        String description = getDescription("event", userInput);
        LocalDate date = getDate(userInput);
        return new Event(description, date);
    }

    public static Deadline stringToDeadline(String userInput) throws DukeException {
        String description = getDescription("deadline", userInput);
        LocalDate date = getDate(userInput);
        return new Deadline(description, date);
    }

    private static String getDescription(String commandUsed, String input) throws DukeException{
        String description;
        int startDescriptionIndex = input.indexOf(commandUsed) + commandUsed.length();
        int endDescriptionIndex = input.indexOf(" /");
        if (startDescriptionIndex < 0) {
            throw new DukeException("Command does not follow pattern <command> <description>...");
        } else {
            if (commandUsed.equals("event") || commandUsed.equals("deadline")) {
                if (endDescriptionIndex < 0) {
                    throw new DukeException("Command does not follow pattern  ... /<at/by> <date in DD-MM-YYYY>");
                } else {
                    description = input.substring(startDescriptionIndex, endDescriptionIndex).trim();
                }
            } else {
                description = input.substring(startDescriptionIndex).trim();
            }
        }
        if (description.equals("")) {
            throw new DukeException("Empty description field");
        }
        return description;
    }

    private static LocalDate getDate(String userInput) throws DukeException {
        try {
            String date = "";
            int startDateIndex = userInput.indexOf("/") + 3;
            if (startDateIndex < 0) {
                throw new DukeException("Command does not follow pattern ... /<at/by> <YYYY-MM-DD>");
            } else {
                date = userInput.substring(startDateIndex).trim();
                if (date.equals("")) {
                    throw new DukeException("Empty date field");
                }
                return LocalDate.parse(date);
            }
        } catch (DateTimeParseException dtpe) {
            throw new DukeException("Date is not valid, require format YYYY-MM-DD");
        }
    }

    /**
     * Extract task number from string input.
     * @param s extracts task number from user input <command> <number>.
     * @return index of the task in the list plus one.
     */
    public static int getTaskNumber(String s, int listSize) throws DukeException {
        // credit: https://stackoverflow.com/questions/14974033/extract-digits-from-string-stringutils-java
        String numberOnly= s.replaceAll("[^0-9]", "");
        int n;
        if (numberOnly.length() <= 0) {
            throw new DukeException("no number given");
        } else {
            n = Integer.parseInt(numberOnly);
            if (n > listSize) {
                throw new DukeException("task does not exist in list");
            } else {
                return n;
            }
        }
}

public static String stringToFind(String userInput) {
     return userInput.substring(userInput.indexOf("find") + 5);
}
}
