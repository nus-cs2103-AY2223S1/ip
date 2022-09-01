package dobby;

import dobby.commands.ByeCommand;
import dobby.commands.Command;
import dobby.commands.DeleteCommand;
import dobby.commands.ErrorCommand;
import dobby.commands.FindCommand;
import dobby.commands.ListCommand;
import dobby.commands.MarkCommand;
import dobby.commands.TaskCommand;
import dobby.commands.UnmarkCommand;

import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Class that deals with understanding user commands.
 */
public class Parser {

    /**
     * Parses the user input and returns the task description of input
     *
     * @param rest The user input
     * @return String of task description
     */
    public static String getDesc(String rest) {
        try {
            int endIndex = rest.indexOf("/") - 1;
            return rest.substring(0, endIndex);
        } catch (NullPointerException | StringIndexOutOfBoundsException e) {
            return "noDate";
        }
    }

    /**
     * Parses the user input and returns the task date of input.
     *
     * @param rest The user input
     * @return String of task date
     */
    public static String getDate(String rest) {
        String dateFormatted = "";
        try {
            int i = rest.indexOf("/");
            if (i == -1) {
                dateFormatted = "noDate";
            } else {
                String dateString = rest.substring(i + 4);
                dateFormatted = dateFormat(dateString, "yyyy-MM-dd HHmm", "MMM dd yyyy HH:mm");
            }
        } catch (DateTimeParseException e) {
            dateFormatted = "wrongDateFormat";
        } catch (StringIndexOutOfBoundsException e) {
            dateFormatted = "wrongDateFormat";
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
        return task.split(" ")[0];
    }

    /**
     * Parses the user input and returns the input without the command.
     *
     * @param task The user input.
     * @return String of user's input without the command
     */
    public static String getRestOfCommand(String task) {
        int firstSpace = task.indexOf(" ");
        String rest = task.substring(firstSpace + 1);
        return rest;
    }

    public static String getDateType(String rest) {
        try {
            int i = rest.indexOf("/");
            if (i == -1) {
                return "noDate";
            } else {
                return rest.substring(i + 1, i + 3);
            }
        } catch (StringIndexOutOfBoundsException e) {
            return "noDate";
        }
    }

    //methods for interpreting .txt file
    /**
     * Parses the task in the saved file and returns the status of the task
     *
     * @param input String of task
     * @return Status of task
     */
    public static boolean getStatusTxt(String input) {
        boolean isDone = input.charAt(5) == 'X';
        return isDone;
    }

    /**
     * Parses the task in the saved file and returns the task type
     *
     * @param input String of task
     * @return Status of task
     */
    public static String getTaskTypeTxt(String input) {
        String task = Character.toString(input.charAt(0));
        return task;
    }

    /**
     * Parses the task in the saved file and returns the string of the task without the task type
     *
     * @param input String of task
     * @return String of task without task type
     */
    public static String getRestTxt(String input) {
        String rest = input.substring(10);
        return rest;
    }

    /**
     * Parses the input string and returns the task description
     *
     * @param rest The input string
     * @return Task description
     */
    public static String getDescTxt(String rest) {
        int endIndex = rest.indexOf("|") - 1;
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
        int startIndex = rest.indexOf("|") + 2;
        String date = rest.substring(startIndex);
        return date;
    }

    /**
     * Converts the input string of date and returns in a specified date format.
     *
     * @param dateString Input string of date
     * @param dateInputPattern Specified input date format
     * @param dateOutputPattern Specified output date format
     * @return
     */
    public static String dateFormat(String dateString, String dateInputPattern, String dateOutputPattern) {
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
        default:
            return new ErrorCommand();
        }
    }
}
