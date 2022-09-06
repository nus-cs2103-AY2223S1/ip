package utility;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String stringCommand = extractCommand(userInput);
        assert !stringCommand.equals(" ");
        switch (stringCommand) {
        case "todo":
            return new AddTaskCommand();
        case "event":
            return new AddEventCommand();
        case "deadline":
            return new AddDeadlineCommand();
        case "delete":
            return new DeleteTaskCommand();
        case "mark":
            return new MarkCommand();
        case "unmark":
            return new UnmarkCommand();
        case "istoday":
            return new CheckIsTodayCommand();
        case "longdesc":
            return new GetLongDescriptionCommand();
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "help":
            return new HelpCommand();
        case "find":
            return new FindCommand();
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
    private static String extractCommand(String input) throws DukeException{
        int whiteSpaceIndex = input.indexOf(" ");
        String command;
        if (whiteSpaceIndex < 0) {
            command = input;
        } else {
            command = input.substring(0, input.indexOf(" "));
        }
        if (command.equals("t") || command.equals("todo")) {
            return "todo";
        } else if (command.equals("l") || command.equals("list")) {
            return "list";
        } else if (command.equals("event") || command.equals("e")) {
            return "event";
        } else if (command.equals("deadline") || command.equals("d")) {
            return "deadline";
        } else if (command.equals("mark") || command.equals("m")) {
            return "mark";
        } else if (command.equals("unmark")  || command.equals("um")) {
            return "unmark";
        } else if (command.equals("bye") || command.equals("quit") || command.equals("q")) {
            return "bye";
        } else if (command.equals("find") || command.equals("f")) {
            return "find";
        } else if (command.equals("longdesc")) {
            return "longdesc";
        } else if (command.equals("istoday")) {
            return "istoday";
        }else {
            return " ";
        }
    }

    public static void main(String[] args)  {
        try {
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            System.out.println(extractCommand(line));
        } catch (Exception e) {
            System.out.println(e);
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
        return ;
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
