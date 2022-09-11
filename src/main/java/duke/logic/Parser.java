package duke.logic;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.function.Supplier;
import java.util.HashMap;

import duke.command.ByeCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.exception.IllegalDescriptionException;
import duke.exception.IllegalKeywordException;
import duke.exception.IllegalTaskException;
import duke.exception.IllegalDateException;
import duke.exception.IllegalTokenException;

/**
 * Parser deals with making sense of user input.
 *
 * @author totsukatomofumi
 */
public class Parser {
    /** HashMap to contain abbreviated month and their numerical representation as a string pairs. */
    private static HashMap<String, String> months = new HashMap<>();

    static {
        String[] monthsArr = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
        int monthNum = 1;
        for (String key : monthsArr) {
            months.put(key, "" + monthNum);
            ++monthNum;
        }
    }

    /** Task list to be passed into command constructors. */
    private TaskList taskList;

    /**
     * Constructor for the parser.
     *
     * @param taskList the task list to be passed into the construction of commands.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses the user input to the appropriate command called, if any.
     *
     * @param response the user input.
     * @return the command based on the user input.
     */
    public Supplier<String> parse(String response) {
        //strip() to allow for any (unintentional) whitespaces before or after
        response = response.strip();
        //for any caps commands
        String responseInLowerCase = response.toLowerCase();

        //program queries
        if (responseInLowerCase.equals("bye")) {
            return this.executeOnBye();
        }

        //list task queries
        try {
            if (responseInLowerCase.equals("list")) {
                return this.executeOnList();
            } else if (responseInLowerCase.equals("find") || responseInLowerCase.startsWith("find ")) {
                return this.executeOnFind(response);
            }
        } catch (IllegalKeywordException e) {
            return () -> "I'm sorry, but you have not specified a keyword.";
        }

        //modify task queries
        try {
            //mark command
            if (responseInLowerCase.equals("mark") || responseInLowerCase.startsWith("mark ")) {
                return this.executeOnMark(response);
            //unmark command
            } else if (responseInLowerCase.equals("unmark") || responseInLowerCase.startsWith("unmark ")) {
                return this.executeOnUnmark(response);
            //delete command
            } else if (responseInLowerCase.equals("delete") || responseInLowerCase.startsWith("delete ")) {
                return this.executeOnDelete(response);
            }
        } catch (NumberFormatException | IllegalTaskException e) {
            return () -> "I'm sorry, but the task number you have inputted does not exist or is invalid.";
        }

        //creation of task queries
        try {
            if (responseInLowerCase.equals("todo") || responseInLowerCase.startsWith("todo ")) {
                return this.executeOnTodo(response);
            } else if (responseInLowerCase.equals("deadline") || responseInLowerCase.startsWith("deadline ")) {
                return this.executeOnDeadline(response);
            } else if (responseInLowerCase.equals("event") || responseInLowerCase.startsWith("event ")) {
                return this.executeOnEvent(response);
            }
        } catch (IllegalDescriptionException e) {
            return () -> "I'm sorry, but the description cannot be empty.";
        } catch (IllegalDateException e) {
            return () -> "I'm sorry, but the date specified is either invalid or empty.";
        } catch (IllegalTokenException e) {
            return () -> "I'm sorry, but you are mising the \"/by\" or \"/at\" token.";
        }

        return () -> "I'm sorry, but I don't know what that means.";

    }

    private Supplier<String> executeOnBye() {
        return new ByeCommand();
    }

    private Supplier<String> executeOnList() {
        return new ListCommand(taskList);
    }

    private Supplier<String> executeOnMark(String response) throws IllegalTaskException {
        //no task specified, mark<space> is 5 char long
        if (response.length() < 6) {
            throw new IllegalTaskException("No task specified.");
        }
        //taking index 5 inclusive onward
        //minus 1 to convert into index starting from 0
        int query = Integer.parseInt(response.substring(5).strip()) - 1;
        //will throw illegaltaskexception
        return new MarkCommand(taskList, query);
    }

    private Supplier<String> executeOnUnmark(String response) throws IllegalTaskException {
        //no task specified, unmark<space> is 7 char long
        if (response.length() < 8) {
            throw new IllegalTaskException("No task specified.");
        }
        //taking index 7 inclusive onward
        //minus 1 to convert into index starting from 0
        int query = Integer.parseInt(response.substring(7).strip()) - 1;
        //will throw illegaltaskexception
        return new UnmarkCommand(taskList, query);
    }

    private Supplier<String> executeOnDelete(String response) throws IllegalTaskException {
        //no task specified, delete<space> is 7 char long
        if (response.length() < 8) {
            throw new IllegalTaskException("No task specified.");
        }
        //taking index 7 inclusive onward
        //minus 1 to convert into index starting from 0
        int query = Integer.parseInt(response.substring(7).strip()) - 1;
        return new DeleteCommand(taskList, query);
    }

    private Supplier<String> executeOnFind(String response) throws IllegalKeywordException {
        //no keywords specified, find<space> is 5 char long
        if (response.length() < 6) {
            throw new IllegalKeywordException("No keyword specified.");
        }
        //taking index 5 inclusive onward
        //ensure no leading whitespaces
        return new FindCommand(taskList, response.substring(5).stripLeading());
    }

    private Supplier<String> executeOnTodo(String response) throws IllegalDescriptionException {
        //completely nothing after command, todo<space> is 5 char long
        //todo command with whitespaces followed are not valid descriptions.
        if (response.length() < 6) {
            throw new IllegalDescriptionException("No description specified.");
        }
        //taking index 5 onward as description
        //should be atleast 1 char by now
        String description = response.substring(5);
        return new ToDoCommand(taskList, description);
    }

    private Supplier<String> executeOnDeadline(String response) throws IllegalTokenException,
            IllegalDescriptionException, IllegalDateException {
        //completely nothing after command, deadline<space> is 9 char long
        if (response.length() < 10) {
            throw new IllegalTokenException("No token found.");
        }
        //cut away deadline<space>
        response = response.substring(9);
        //e.g. deadline /by <date>
        //or deadline /by
        if (response.equals("/by") || response.startsWith("/by ")) {
            throw new IllegalDescriptionException("No description specified.");
        }
        //e.g. deadline <desc> /by
        if (response.endsWith(" /by")) {
            throw new IllegalDateException("No date specified.");
        }
        int tokenPosition = response.indexOf(" /by ");
        if (tokenPosition < 0) {
            throw new IllegalTokenException("No token found.");
        }
        String description = response.substring(0, tokenPosition);
        //starting from after /by<space>
        String date = response.substring(tokenPosition + 5);
        //as of now still can have e.g deadline <space><space><space> /by <date>
        //to ensure whitespaces alone as a description or date is not allowed
        if (description.strip().length() == 0) {
            throw new IllegalDescriptionException("No description specified.");
        }
        //should not even come here as if date is pure whitespace, will be truncated
        if (date.length() == 0) {
            throw new IllegalDateException("No date specified.");
        }
        //strip leading get rid of white spaces infront, back alr dealt with
        date = date.stripLeading();
        return new DeadlineCommand(taskList, description, Parser.parseDate(date));
    }

    private Supplier<String> executeOnEvent(String response) throws IllegalTokenException, IllegalDescriptionException,
            IllegalDateException {
        if (response.length() < 7) {
            throw new IllegalTokenException("No token found.");
        }
        response = response.substring(6);
        if (response.equals("/at") || response.startsWith("/at ")) {
            throw new IllegalDescriptionException("No description specified.");
        }
        if (response.endsWith(" /at")) {
            throw new IllegalDateException("No date specified.");
        }
        int tokenPosition = response.indexOf(" /at ");
        if (tokenPosition < 0) {
            throw new IllegalTokenException("No token found.");
        }
        String description = response.substring(0, tokenPosition);
        String date = response.substring(tokenPosition + 5);
        //to ensure whitespaces alone as a description or date is not allowed
        if (description.strip().length() == 0) {
            throw new IllegalDescriptionException("No description specified.");
        }
        //should not even come here as if date is pure whitespace, will be truncated
        if (date.length() == 0) {
            throw new IllegalDateException("No date specified.");
        }
        //strip leading get rid of white spaces infront, back alr dealt with
        date = date.stripLeading();
        return new EventCommand(taskList, description, Parser.parseDate(date));
    }

    /**
     * Parses string to the appropriate date it represents, if any.
     *
     * @param dateStr the string to parse.
     * @return the LocalDate object representing date.
     * @throws IllegalDateException If unable to parse the string to a valid date.
     */
    private static LocalDate parseDate(String dateStr) throws IllegalDateException {
        String dayStr;
        String monthStr;
        String yearStr;

        String[] dateArr = new String[3]; //ddmmyyyy
        Parser.splitString(dateStr, dateArr);

        if (dateArr[0] == null || dateArr[1] == null) { //need both day and month else invalid
            throw new IllegalDateException("Unable to parse due to invalid format.");
        }

        dayStr = dateArr[0].strip();

        monthStr = dateArr[1].strip();
        //convert month to number
        if (monthStr.length() == 3) { //length of 3 may indicate abbrev
            monthStr = Parser.months.get(monthStr.toLowerCase());
            if (monthStr == null) { //no such month
                throw new IllegalDateException("Invalid abbreviated month.");
            }
        }

        if (dateArr[2] == null) { //if year null, defaults to current year
            dateArr[2] = "" + LocalDate.now().getYear();
        }
        yearStr = dateArr[2].strip();
        //convert yy to yyyy
        if (yearStr.length() == 2) {
            yearStr = "20" + yearStr;
        }
        //by here, anyth outside length of 4 reject
        if (yearStr.length() != 4) {
            throw new IllegalDateException("Invalid year input.");
        }

        try {
            return LocalDate.of(Integer.parseInt(yearStr), Integer.parseInt(monthStr), Integer.parseInt(dayStr));
        } catch (NumberFormatException | DateTimeException e) {
            //if cannot parse means invalid input
            throw new IllegalDateException("Date input invalid.");
        }
    }

    private static void splitString(String str, String[] dest) {
        String[] result = new String[3];

        if (str.indexOf('/') >= 0) {
            result = Parser.splitStringByChar('/', str);
        } else if (str.indexOf('-') >= 0) {
            result = Parser.splitStringByChar('-', str);
        } else if (str.indexOf('.') >= 0) {
            result = Parser.splitStringByChar('.', str);
        } else if (str.indexOf(' ') >= 0) {
            result = Parser.splitStringByChar(' ', str);
        } else if (str.length() == 4 || str.length() == 6 || str.length() == 8) { //no char separator
            result[0] = str.substring(0, 2);
            result[1] = str.substring(2, 4);
            if (str.length() > 4) {
                result[2] = str.substring(4);
            }
        }
        //copy result to dest arr
        for (int i = result.length - 1; i >= 0; --i) {
            dest[i] = result[i];
        }
    }

    /**
     * Splits a string by using a separator char.
     *
     * @param separator the char used as separator.
     * @param str the string to split.
     * @return an array of parts of the input string in order.
     */
    private static String[] splitStringByChar(char separator, String str) {
        //helper that just splits string by specified char separator into array of size 3
        String[] arr = new String[3];
        int currIndex = 0;
        for (int charIndex = str.indexOf(separator);
             charIndex >= 0 && currIndex < 2;
             charIndex = str.indexOf(separator)) {
            arr[currIndex++] = str.substring(0, charIndex);
            str = str.substring(charIndex + 1);
        }
        arr[currIndex] = str;
        return arr;
    }
}
