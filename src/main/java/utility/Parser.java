package utility;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
    private static HashMap<String, Pattern> commandAliasesHashMap = new HashMap<>();
    private static final int NO_OF_COMMANDS_SUPPORTED =12;

    /**
     * Initializes hashmap which stores Patterns
     * required to determine String command used.
     *
     */
    public static void initialiseCommandAliasesHashMap() {
        String[] aliasRegexes = new String[]{"todo|task|t", "l|list",
                "deadline|d", "event|e", "mark|m", "unmark|um","bye|b|quit|q|exit",
                "find|f", "longdesc", "istoday", "help|h", "delete|remove|r"
        };
        String[] actualCommands = new String[] {"todo", "list", "deadline", "event",
            "mark", "unmark", "bye", "find", "longdesc", "istoday", "help", "delete"};

        if (commandAliasesHashMap.isEmpty()) {
            ArrayList<Pattern> patterns = makePatterns(aliasRegexes);
            for(int i = 0; i < NO_OF_COMMANDS_SUPPORTED; i ++) {
                commandAliasesHashMap.put(actualCommands[i], patterns.get(i));
            }
        }
    }

    /**
     * Converts a string of regex to Pattern
     * which can be used to find matches.
     * Pattern created is case-insensitive.
     *
     * @param regexes array of regexes to be converted.
     * @return ArrayList of patterns.
     */
    private static ArrayList<Pattern> makePatterns(String[] regexes) {
        ArrayList<Pattern> patterns = new ArrayList<>();
        Pattern tempPattern;
        for (String alias: regexes) {
            tempPattern =  Pattern.compile(alias,
                    Pattern.CASE_INSENSITIVE);
            patterns.add(tempPattern);
        }
        return patterns;
    }

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
        switch (stringCommand) {
        case "todo":
            Task t;
            return new AddTaskCommand(task);
        case "event":
            Event event;
            return new AddEventCommand(event);
        case "deadline":
            Deadline deadline;
            return new AddDeadlineCommand(deadline);
        case "delete":
            int index;
            return new DeleteTaskCommand(index);
        case "mark":
            int index;
            return new MarkCommand(index);
        case "unmark":
            int index;
            return new UnmarkCommand(index);
        case "istoday":
            int index;
            return new CheckIsTodayCommand(index);
        case "longdesc":
            int index;
            return new GetLongDescriptionCommand();
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "help":
            return new HelpCommand();
        case "find":
            String keyword;
            return new FindCommand(keyword);
        default:
            String message = "Command invalid. Type help for more information."
                    + stringCommand;
            throw new DukeException(message);
        }
    }


    /**
     * Matches command to its formal format, and
     * returns the same.
     * If command has no match, returns empty string.
     *
     * @param command Command to be matched.
     * @return Formal format of command.
     */
    private static String extractCommand(String command) {
        // Make sure commandAliases are not empty
        initialiseCommandAliasesHashMap();
        Matcher matcher;
        for (Map.Entry<String, Pattern> patternAndString : commandAliasesHashMap.entrySet()) {
            matcher = patternAndString.getValue().matcher(command);
            if (matcher.matches()) {
                return patternAndString.getKey();
            }
        }
        return "";
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
     * Returns event.
     *
     * @param description description
     * @param date date
     * @return event
     * @throws DukeException error
     */
    public static Event stringToEvent(String description, String date) throws DukeException {
        LocalDate localDate = getDate(date);
        return new Event(description, localDate);
    }

    /**
     * Return deadline
     *
     * @param description description.
     * @param date date
     * @return deadline
     * @throws DukeException error.
     */
    public static Deadline stringToDeadline(String description, String date) throws DukeException {
        LocalDate localDate = getDate(date);
        return new Deadline(description, localDate);
    }


    /**
     * Get date
     *
     * @param date date
     * @return date
     * @throws DukeException date error.
     */
    private static LocalDate getDate(String date) throws DukeException {
        try {
            return LocalDate.parse(date);
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
