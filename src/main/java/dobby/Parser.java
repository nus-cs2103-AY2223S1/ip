package dobby;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import dobby.commands.ByeCommand;
import dobby.commands.Command;
import dobby.commands.DeleteCommand;
import dobby.commands.ErrorCommand;
import dobby.commands.FindCommand;
import dobby.commands.ListCommand;
import dobby.commands.MarkCommand;
import dobby.commands.SimplifyCommand;
import dobby.commands.TaskCommand;
import dobby.commands.UnmarkCommand;


/**
 * Class that deals with understanding user commands.
 */
public class Parser {
    private static final String DESC_AND_DATE_SEPERATOR = "/";
    private static final String TASKTYPE_AND_REST_SEPERATOR = " ";
    private static final String NO_DATE = "noDate";
    private static final String NO_DESCRIPTION = "noDescription";
    private static final String WRONG_DATE_FORMAT = "wrongDateFormat";
    private static final char TASK_MARKED_SYMBOL = 'X';
    private static final String USERFILE_DESC_DATE_SEPERATOR = "|";
    private static final String INPUT_DATE_FORMAT = "yyyy-MM-dd HHmm";
    private static final String OUTPUT_DATE_FORMAT = "MMM dd yyyy HH:mm";

    /**
     * Parses the user input and returns the task description of input.
     *
     * @param rest The user input
     * @return String of task description
     */
    public static String getDesc(String rest) {
        try {
            assert rest != null : "string input cannot be null!";
            int endIndex = rest.indexOf(DESC_AND_DATE_SEPERATOR) - 1;
            return rest.substring(0, endIndex);
        } catch (NullPointerException | StringIndexOutOfBoundsException e) {
            return NO_DESCRIPTION;
        }
    }

    /**
     * Parses the user input and returns the task date of input.
     *
     * @param rest The user input
     * @return String of task date
     */
    public static String getDate(String rest) {
        String dateFormatted;
        try {
            assert rest != null : "string input cannot be null!";
            int i = rest.indexOf(DESC_AND_DATE_SEPERATOR);
            if (i == -1) {
                dateFormatted = NO_DATE;
            } else {
                String dateString = rest.substring(i + 4);
                dateFormatted = dateFormat(dateString, INPUT_DATE_FORMAT, OUTPUT_DATE_FORMAT);
            }
        } catch (DateTimeParseException e) {
            dateFormatted = WRONG_DATE_FORMAT;
        } catch (StringIndexOutOfBoundsException e) {
            dateFormatted = WRONG_DATE_FORMAT;
        }
        return dateFormatted;
    }

    /**
     * Parses the user input and returns the user's command.
     *
     * @param task The user input
     * @return String of command of user's input
     */
    public static String getTaskType(String task) {
        assert task != null : "string input cannot be null!";
        return task.split(TASKTYPE_AND_REST_SEPERATOR)[0];
    }

    /**
     * Parses the user input and returns the input without the command, aka the "rest" of the command
     *
     * @param task The user input.
     * @return String of user's input without the command
     */
    public static String getRestOfCommand(String task) {
        assert task != null : "string input cannot be null!";
        int firstSpace = task.indexOf(TASKTYPE_AND_REST_SEPERATOR);
        String rest = task.substring(firstSpace + 1);
        return rest;
    }

    /**
     * Parses date type (deadline or event) from the user input without the command.
     *
     * @param rest user input without command
     * @return date type, deadline or event
     */
    public static String getDateType(String rest) {
        try {
            assert rest != null : "string input cannot be null!";
            int i = rest.indexOf(DESC_AND_DATE_SEPERATOR);
            if (i == -1) {
                return NO_DATE;
            } else {
                return rest.substring(i + 1, i + 3);
            }
        } catch (StringIndexOutOfBoundsException e) {
            return NO_DATE;
        }
    }

    /**
     * Returns default shortened command from input without the command.
     *
     * @param rest input without command
     * @return default shortened command
     */
    public static String getInitialCommand(String rest) {
        assert rest != null : "string input cannot be null!";
        if (rest.isBlank()) {
            return "";
        } else {
            String oldCommand = rest.split(TASKTYPE_AND_REST_SEPERATOR)[0];
            return oldCommand;
        }
    }

    /**
     * Returns user custom defined shortened command from input without the command.
     *
     * @param rest input without command
     * @return default shortened command
     */
    public static String getNewCommand(String rest) {
        assert rest != null : "string input cannot be null!";
        if (rest.isBlank()) {
            return "";
        } else {
            String newCommand = rest.split(TASKTYPE_AND_REST_SEPERATOR)[1];
            return newCommand;
        }
    }

    //methods for interpreting .txt file

    /**
     * Parses the task in the saved file and returns the status of the task.
     *
     * @param input String of task
     * @return Status of task
     */
    public static boolean getStatusTxt(String input) {
        assert input != null : "string input cannot be null!";
        boolean isDone = (input.charAt(5) == TASK_MARKED_SYMBOL);
        return isDone;
    }

    /**
     * Parses the task in the saved file and returns the task type.
     *
     * @param input String of task
     * @return Status of task
     */
    public static String getTaskTypeTxt(String input) {
        assert input != null : "string input cannot be null!";
        String task = Character.toString(input.charAt(0));
        return task;
    }

    /**
     * Parses the task in the saved file and returns the string of the task without the task type.
     *
     * @param input String of task
     * @return String of task without task type
     */
    public static String getRestTxt(String input) {
        assert input != null : "string input cannot be null!";
        String rest = input.substring(10);
        return rest;
    }

    /**
     * Parses the input string and returns the task description.
     *
     * @param rest The input string
     * @return Task description
     */
    public static String getDescTxt(String rest) {
        assert rest != null : "string input cannot be null!";
        int endIndex = rest.indexOf(USERFILE_DESC_DATE_SEPERATOR) - 1;
        String desc = rest.substring(0, endIndex);
        return desc;
    }

    /**
     * Parses the input string and returns the task date.
     *
     * @param rest The input string
     * @return Task date
     */
    public static String getDateTxt(String rest) {
        assert rest != null : "string input cannot be null!";
        int startIndex = rest.indexOf(USERFILE_DESC_DATE_SEPERATOR) + 2;
        String date = rest.substring(startIndex);
        return date;
    }

    /**
     * Converts the input string of date and returns in a specified date format.
     *
     * @param dateString Input string of date
     * @param dateInputPattern Specified input date format
     * @param dateOutputPattern Specified output date format
     * @return date in formatted to the specified output date format
     */
    public static String dateFormat(String dateString,
                                    String dateInputPattern, String dateOutputPattern) {
        //date format for intended input
        DateTimeFormatter form = DateTimeFormatter.ofPattern(dateInputPattern);
        LocalDateTime date = LocalDateTime.parse(dateString, form);
        String dateFormatted = date.format(DateTimeFormatter.ofPattern(dateOutputPattern));
        return dateFormatted;
    }

    /**
     * Method that parses the users command and returns the corresponding command object.
     *
     * @param cmd User's command
     * @return Corresponding command object.
     */
    public static Command parse(String cmd) {
        assert cmd != null : "command cannot be null";
        switch (cmd) {
        case "bye":
        case "quit":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand();
        case "unmark":
            return new UnmarkCommand();
        case "delete":
            return new DeleteCommand();
        case "todo":
        case "event":
        case "deadline":
            return new TaskCommand();
        case "find":
            return new FindCommand();
        case "simplify":
            return new SimplifyCommand();
        default:
            return new ErrorCommand();
        }
    }
}
