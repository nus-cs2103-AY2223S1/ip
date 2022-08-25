package utility;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddTaskCommand;
import command.CheckIsTodayCommand;
import command.Command;
import command.DeleteTaskCommand;
import command.ExitCommand;
import command.FindCommand;
import command.GetLongDescriptionCommand;
import command.HelpCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import exceptions.DukeException;
import task.Deadline;
import task.Event;
import task.Task;


/**
 * Handles all conversions required in the program.
 */
public class Parser {

    /**
     * Returns Command object correspond to
     * command extracted from user input.
     * @param userInput User input string to parse into Command.
     * @return Command type object.
     * @throws DukeException When command given is invalid.
     */
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
        } else if (stringCommand.equals("deadline")) {
            return new AddDeadlineCommand();
        } else if (stringCommand.equals("delete")) {
            return new DeleteTaskCommand();
        } else if (stringCommand.equals("mark")) {
            return new MarkCommand();
        } else if (stringCommand.equals("unmark")) {
            return new UnmarkCommand();
        } else if (stringCommand.equals("istoday")) {
            return new CheckIsTodayCommand();
        } else if (stringCommand.equals("longdesc")) {
            return new GetLongDescriptionCommand();
        } else if (stringCommand.equals("list")) {
            return new ListCommand();
        } else if (stringCommand.equals("bye")) {
            return new ExitCommand();
        } else if (stringCommand.equals("help")) {
            return new HelpCommand();
        } else if (stringCommand.equals("find")) {
            return new FindCommand();
        }
        throw new DukeException("Command invalid. Type help for more information.");
    }

    /**
     * Converts string command for adding
     * task to corresponding Task object.
     * @param userInput User input command for creating Task.
     * @return Task object with required description.
     * @throws DukeException When no valid description is found.
     */
    public static Task stringToTask(String userInput) throws DukeException {
        String description = getDescription("todo", userInput);
        return new Task(description);
    }

    /**
     * Converts string command for adding
     * event to corresponding Event object.
     * @param userInput User input command for creating Event.
     * @return Event object with required description and date.
     * @throws DukeException When no valid description or date is found.
     */
    public static Event stringToEvent(String userInput) throws DukeException {
        String description = getDescription("event", userInput);
        LocalDate date = getDate(userInput);
        return new Event(description, date);
    }

    /**
     * Converts string command for adding
     * deadline to corresponding Deadline object.
     * @param userInput User input command for creating Deadline.
     * @return Deadline object with required description and date.
     * @throws DukeException When no valid description or date is found.
     */
    public static Deadline stringToDeadline(String userInput) throws DukeException {
        String description = getDescription("deadline", userInput);
        LocalDate date = getDate(userInput);
        return new Deadline(description, date);
    }

    /**
     * Extracts description of a task from user input.
     * Requires command type used to evaluate description.
     * @param commandUsed Command type mentioned in user input.
     * @param input User input string for performing command.
     * @return Description contained in user input.
     * @throws DukeException when no valid description is found.
     */
    private static String getDescription(String commandUsed, String input) throws DukeException {
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

    /**
     * Extracts date from a date-able Task.
     * @param userInput User input command to extract date from.
     * @return LocalDate object corresponding to date mentioned.
     * @throws DukeException Throws when no valid date found.
     */
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
     * Extracts task number from user input string.
     * @param s User input string to get number from.
     * @param listSize TaskList size to check if number is valid.
     * @return Index of the task in the list plus one.
     * @throws DukeException when conditions not met.
     */
    public static int getTaskNumber(String s, int listSize) throws DukeException {
        // credit: https://stackoverflow.com/questions/14974033/extract-digits-from-string-stringutils-java
        String numberOnly = s.replaceAll("[^0-9]", "");
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

    /**
     * Extracts keyword to user is looking up from user input.
     * @param userInput User input to extract keyword from.
     * @return Keyword required to perform Find operation.
     */
    public static String stringToFind(String userInput) {
        return userInput.substring(userInput.indexOf("find") + 5);
    }
}
