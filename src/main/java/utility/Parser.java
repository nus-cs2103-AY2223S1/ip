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
        String[] inputSections = userInput.split(" ");
        String stringCommand = extractCommand(inputSections[0]);
        assert !stringCommand.equals(" ");
        switch (stringCommand) {
        case "todo":
            return new AddTaskCommand(inputSections);
        case "event":
            return new AddEventCommand(inputSections);
        case "deadline":
            return new AddDeadlineCommand(inputSections);
        case "delete":
            return new DeleteTaskCommand(inputSections);
        case "mark":
            return new MarkCommand(inputSections);
        case "unmark":
            return new UnmarkCommand(inputSections);
        case "istoday":
            return new CheckIsTodayCommand(inputSections);
        case "longdesc":
            return new GetLongDescriptionCommand(inputSections);
        case "list":
            return new ListCommand(inputSections);
        case "bye":
            return new ExitCommand(inputSections);
        case "help":
            return new HelpCommand(inputSections);
        case "find":
            return new FindCommand(inputSections);
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
    private static String extractCommand(String input) {
        int whiteSpaceIndex = input.indexOf(" ");
        String command;
        if (whiteSpaceIndex < 0) {
            command = input;
        }
        else {
            command = input.substring(0, input.indexOf(" "));
        }
        if (command.equalsIgnoreCase("t") || command.equalsIgnoreCase("todo")) {
            return "todo";
        }
        else if (command.equalsIgnoreCase("l") || command.equalsIgnoreCase("list")) {
            return "list";
        }
        else if (command.equalsIgnoreCase("deadline") || command.equalsIgnoreCase("d")) {
            return "deadline";
        }
        else if (command.equalsIgnoreCase("mark") || command.equalsIgnoreCase("m")) {
            return "mark";
        }
        else if (command.equalsIgnoreCase("unmark") || command.equalsIgnoreCase("um")) {
            return "unmark";
        }
        else if (command.equalsIgnoreCase("bye") || command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("q") ||
                command.equalsIgnoreCase("b") || command.equalsIgnoreCase("exit")) {
            return "bye";
        }
        else if (command.equalsIgnoreCase("find") || command.equalsIgnoreCase("f")) {
            return "find";
        }
        else if (command.equalsIgnoreCase("longdesc")) {
            return "longdesc";
        }
        else if (command.equalsIgnoreCase("istoday")) {
            return "istoday";
        }  else if (command.equalsIgnoreCase("help") || command.equalsIgnoreCase("h") ) {
            return "help";
        }
        else {
            return " ";
        }
    }

    /**
     * Converts string command for adding
     * task to corresponding Task object.
     *
     * @param description User input command for creating Task.
     * @return Task object with required description.
     * @throws DukeException When no valid description is found.
     */
    public static Task stringToTask(String description) throws DukeException {
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
    public static Event stringToEvent(String description, String date) throws DukeException {
        LocalDate localDate = getDate(date);
        return new Event(description, localDate);
    }

    /**
     * Converts string command for adding
     * deadline to corresponding Deadline object.
     *
     * @param userInput User input command for creating Deadline.
     * @return Deadline object with required description and date.
     * @throws DukeException When no valid description or date is found.
     */
    public static Deadline stringToDeadline(String description, String date) throws DukeException {
        LocalDate localDate = getDate(date);
        return new Deadline(description, localDate);
    }


    /**
     * Extracts date from a date-able Task.
     *
     * @param userInput User input command to extract date from.
     * @return LocalDate object corresponding to date mentioned.
     * @throws DukeException Throws when no valid date found.
     */
    private static LocalDate getDate(String date) throws DukeException {
        try {
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

    public static void main(String[] args) throws DukeException {
        System.out.println(Parser.parse("h"));
    }
}
