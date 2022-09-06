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
    private static final String END_OF_DESCRIPTION_MARKER = " /";
    private static final int START_DATE_COMMAND_LENGTH = 3;
    private static final String EVENT_DATE_SECTION_START_STRING = "/at";
    private static final String DEADLINE_DATE_SECTION_DATE_STRING = "/by";

    /**
     * Returns Command object corresponding to
     * command extracted from user input.
     * Looks for first whitespace to distinguish
     * command used.
     *
     * @param userInput User input string to parse into Command.
     * @return Command type object.
     * @throws DukeException When command given is invalid.
     */
    public static Command parse(String userInput) throws DukeException {
        String[] stringCommand = extractCommand(userInput);
        assert !stringCommand[0].equals(" ");
        switch (stringCommand[0]) {
        case "todo":
            return new AddTaskCommand(stringCommand[1]);
        case "event":
            return new AddEventCommand(stringCommand[1]);
        case "deadline":
            return new AddDeadlineCommand(stringCommand[1]);
        case "delete":
            return new DeleteTaskCommand(stringCommand[1]);
        case "mark":
            return new MarkCommand(stringCommand[1]);
        case "unmark":
            return new UnmarkCommand(stringCommand[1]);
        case "istoday":
            return new CheckIsTodayCommand(stringCommand[1]);
        case "longdesc":
            return new GetLongDescriptionCommand(stringCommand[1]);
        case "list":
            return new ListCommand(stringCommand[1]);
        case "bye":
            return new ExitCommand(stringCommand[1]);
        case "help":
            return new HelpCommand(stringCommand[1]);
        case "find":
            return new FindCommand(stringCommand[1]);
        default:
            String message = "Command invalid. Type help for more information." + stringCommand;
            throw new DukeException(message);
        }
    }

    /**
     *
     * Extracts shortcut or formal command used and returns formal command/
     * If input does not follow valid format, return empty string.
     *
     * @param input
     * @return
     */
    private static String[] extractCommand(String input) throws DukeException{
        int whiteSpaceIndex = input.indexOf(" ");
        String command;
        if (whiteSpaceIndex < 0) {
            command = input;
        } else {
            command = input.substring(0, input.indexOf(" "));
        }
        if (command.equalsIgnoreCase("t") || command.equalsIgnoreCase("todo")) {
            return new String[]{"todo", command};
        } else if (command.equalsIgnoreCase("l") || command.equalsIgnoreCase("list")) {
            return new String[]{"list", command};
        } else if (command.equalsIgnoreCase("deadline") || command.equalsIgnoreCase("d")) {
            return new String[]{"deadline", command};
        } else if (command.equalsIgnoreCase("mark") || command.equalsIgnoreCase("m")) {
            return new String[]{"mark", command};
        } else if (command.equalsIgnoreCase("unmark")  || command.equalsIgnoreCase("um")) {
            return new String[]{"unmark", command};
        } else if (command.equalsIgnoreCase("bye") || command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("q") ||
                command.equalsIgnoreCase("b") || command.equalsIgnoreCase("exit")){
            return new String[]{"bye", command};
        } else if (command.equalsIgnoreCase("find") || command.equalsIgnoreCase("f")) {
            return new String[]{"find", command};
        } else if (command.equalsIgnoreCase("longdesc")) {
            return new String[]{"longdesc", command};
        } else if (command.equalsIgnoreCase("istoday")) {
            return new String[]{"istoday", command};
        }else {
            return new String[]{" ", command};
        }
    }

    /**
     * Converts string command for adding
     * task to corresponding Task object.
     *
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
     *
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
     *
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
     *
     * @param commandUsed Command type mentioned in user input.
     * @param input User input string for performing command.
     * @return Description contained in user input.
     * @throws DukeException when no valid description is found.
     */
    private static String getDescription(String commandUsed, String input) throws DukeException {

    }

    /**
     * Extracts date from a date-able Task.
     *
     * @param userInput User input command to extract date from.
     * @return LocalDate object corresponding to date mentioned.
     * @throws DukeException Throws when no valid date found.
     */
    private static LocalDate getDate(String userInput) throws DukeException {
        try {
            String date;
            int n = userInput.indexOf(EVENT_DATE_SECTION_START_STRING);
            int m = userInput.indexOf(DEADLINE_DATE_SECTION_DATE_STRING);
            int startDateIndex = (n == -1 ? m : n) + START_DATE_COMMAND_LENGTH;
            date = userInput.substring(startDateIndex).trim();
            if (!date.isBlank()) {
                return LocalDate.parse(date);
            } else {
                throw new DukeException("Empty date field");
            }
        } catch (DateTimeParseException dtpe) {
            throw new DukeException("Date is not valid, require format YYYY-MM-DD");
        }
    }


    /**
     * Extracts task number from user input string.
     *
     * @param s User input string to get number from.
     * @param listSize TaskList size to check if number is valid.
     * @return Index of the task in the list plus one.
     * @throws DukeException when conditions not met.
     */
    public static int getTaskNumber(String s, int listSize) throws DukeException {
        // credit: https://stackoverflow.com/questions/14974033/extract-digits-from-string-stringutils-java
        String numberOnly = s.replaceAll("[^0-9]", "");
        if (numberOnly.length() <= 0) {
            throw new DukeException("no number given");
        }
        int n = Integer.parseInt(numberOnly);
        if (n <= listSize) {
            return n;
        } else {
            throw new DukeException("Task does not exist in list");
        }

    }

    /**
     * Extracts keyword to user is looking up from user input.
     *
     * @param userInput User input to extract keyword from.
     * @return Keyword required to perform Find operation.
     */
    public static String stringToFind(String userInput) {
        return userInput.substring(userInput.indexOf("find") + 5);
    }
}
