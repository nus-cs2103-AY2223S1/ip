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
        int index;
        switch (stringCommand) {
        case "todo":
            Task task = createTask(inputSections);
            return new AddTaskCommand(task);
        case "event":
            Event event = createEvent(inputSections);
            return new AddEventCommand(event);
        case "deadline":
            Deadline deadline = createDeadline(inputSections);
            return new AddDeadlineCommand(deadline);
        case "delete":
            index = getIndex(inputSections);
            return new DeleteTaskCommand(index);
        case "mark":
            index = getIndex(inputSections);
            return new MarkCommand(index);
        case "unmark":
            index = getIndex(inputSections);
            return new UnmarkCommand(index);
        case "istoday":
            index = getIndex(inputSections);
            return new CheckIsTodayCommand(index);
        case "longdesc":
            index = getIndex(inputSections);
            return new GetLongDescriptionCommand(index);
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "help":
            return new HelpCommand();
        case "find":
            String keyword = getKeyword(inputSections);
            return new FindCommand(keyword);
        default:
            String message = "Command invalid. Type help for more information."
                    + stringCommand;
            throw new DukeException(message);
        }
    }

    private static Task createTask(String[] userInput) throws DukeException {
        String description = getTaskDescription(userInput);
        Task task = new Task(description);
        return task;
    }

    private static String getTaskDescription(String[] userInput) throws DukeException {
        if (userInput.length >= 2) {
            String description = getStringBetweenIndices(1, userInput.length, userInput);
            return description;
        } else {
            throw new DukeException("Invalid description provided");
        }
    }

    private static String getStringBetweenIndices(int i, int j, String[] array) {
        StringBuffer str = new StringBuffer();
        for (; i < j; i++) {
            str.append(array[i]);
        }
        return str.toString();
    }

    private static Event createEvent(String[] userInput) throws DukeException{
        String description = getEventDescription(userInput);
        LocalDate date = getEventDate(userInput);
        Event event = new Event(description, date);
        return event;
    }

    private static String getEventDescription(String[] userInput) throws DukeException{
        if (userInput.length >= 2) {
            int startIndexOfDescription = 1;
            int endIndexOfDescription = getStartOfDate(userInput);
            String description = getStringBetweenIndices(startIndexOfDescription, endIndexOfDescription, userInput);
            return description;
        } else {
            throw new DukeException("Invalid description provided");
        }
    }

    private static int getStartOfDate(String[] userInput) throws DukeException {
        String regex = "\\d\\d\\d\\d-\\d\\d-\\d\\d";
        Pattern datePattern = Pattern.compile(regex);
        Matcher dateMatcher;
        for (int i = 1;i < userInput.length; i ++) {
            dateMatcher = datePattern.matcher(userInput[i]);
            if (dateMatcher.matches()) {
                return i;
            }
        }
        throw new DukeException("No date given");
    }

    private static LocalDate getEventDate(String[] userInput) throws DukeException{
        int indexOfDate = getStartOfDate(userInput);
        return LocalDate.parse(userInput[indexOfDate]);
    }

    private static Deadline createDeadline(String[] userInput) throws DukeException {
        String description = getDeadlineDescription(userInput);
        LocalDate date = getDeadlineDate(userInput);
        Deadline deadline = new Deadline(description, date);
        return deadline;
    }

    private static String getDeadlineDescription(String[] userInput) throws DukeException {
        return getEventDescription(userInput);
    }

    private static LocalDate getDeadlineDate(String[] userInput) throws DukeException{
        return getEventDate(userInput);
    }

    private static int getIndex(String[] userInput) throws DukeException {
        return findIntInStringArray(userInput);
    }

    private static int findIntInStringArray(String[] array) throws DukeException{
        String intRegex = "\\*d";
        Pattern intPattern = Pattern.compile(intRegex);
        Matcher matchInt;
        for (String s: array) {
            matchInt = intPattern.matcher(s);
            if (matchInt.matches()) {
                return Integer.parseInt(s);
            }
        }
        throw new DukeException("No index given");
    }
    private static String getKeyword(String[] userInput) {
        String keyword = getStringBetweenIndices(1, userInput.length, userInput);
        return keyword;
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
}
