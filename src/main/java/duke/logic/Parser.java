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
import duke.exception.IllegalTimeException;
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
        int dayNum = 1;
        for (String key : monthsArr) {
            months.put(key, "" + dayNum);
            ++dayNum;
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
        String lresponse = response.toLowerCase();
        //bye command
        if (lresponse.equals("bye")) {
            return new ByeCommand();
        //list command
        } else if (lresponse.equals("list")) {
            return new ListCommand(taskList);
        }

        try {
            //mark command
            if (lresponse.equals("mark") || lresponse.startsWith("mark ")) {
                //no task specified, mark<space> is 5 char long
                if (response.length() < 6) {
                    throw new IllegalTaskException("No task specified.");
                }
                //taking index 5 inclusive onward
                //minus 1 to convert into index starting from 0
                int query = Integer.parseInt(response.substring(5).strip()) - 1;
                //will throw illegaltaskexception
                return new MarkCommand(taskList, query);
            //unmark command
            } else if (lresponse.equals("unmark") || lresponse.startsWith("unmark ")) {
                //no task specified, unmark<space> is 7 char long
                if (response.length() < 8) {
                    throw new IllegalTaskException("No task specified.");
                }
                //taking index 7 inclusive onward
                //minus 1 to convert into index starting from 0
                int query = Integer.parseInt(response.substring(7).strip()) - 1;
                //will throw illegaltaskexception
                return new UnmarkCommand(taskList, query);
            //delete command
            } else if (lresponse.equals("delete") || lresponse.startsWith("delete ")) {
                //no task specified, delete<space> is 7 char long
                if (response.length() < 8) {
                    throw new IllegalTaskException("No task specified.");
                }
                //taking index 7 inclusive onward
                //minus 1 to convert into index starting from 0
                int query = Integer.parseInt(response.substring(7).strip()) - 1;
                return new DeleteCommand(taskList, query);
            } else if (lresponse.equals("find") || lresponse.startsWith("find ")) {
                //no keywords specified, find<space> is 5 char long
                if (response.length() < 6) {
                    throw new IllegalKeywordException("No keyword specified.");
                }
                //taking index 5 inclusive onward
                //ensure no leading whitespaces
                return new FindCommand(taskList, response.substring(5).stripLeading());
            }
        } catch (NumberFormatException | IllegalTaskException e) {
            return () -> "I'm sorry, but the task number you have inputted does not exist or is invalid.";
        } catch (IllegalKeywordException e) {
            return () -> "I'm sorry, but you have not specified a keyword.";
        }

        try {
            if (lresponse.equals("todo") || lresponse.startsWith("todo ")) {
                //completely nothing after command, todo<space> is 5 char long
                //todo command with whitespaces followed are not valid descriptions.
                if (response.length() < 6) {
                    throw new IllegalDescriptionException("No description specified.");
                }
                //taking index 5 onward as description
                //should be atleast 1 char by now
                String description = response.substring(5);
                return new ToDoCommand(taskList, description);
            } else if (lresponse.equals("deadline") || lresponse.startsWith("deadline ")) {
                //completely nothing after command, deadline<space> is 9 char long
                if (response.length() < 10) {
                    throw new IllegalTokenException("No token found.");
                }
                //cut away deadline<space>
                response = response.substring(9);
                //e.g. deadline /by <time>
                //or deadline /by
                if (response.equals("/by") || response.startsWith("/by ")) {
                    throw new IllegalDescriptionException("No description specified.");
                }
                //e.g. deadline <desc> /by
                if (response.endsWith(" /by")) {
                    throw new IllegalTimeException("No time specified.");
                }
                int tokenPosition = response.indexOf(" /by ");
                if (tokenPosition < 0) {
                    throw new IllegalTokenException("No token found.");
                }
                String description = response.substring(0, tokenPosition);
                //starting from after /by<space>
                String time = response.substring(tokenPosition + 5);
                //as of now still can have e.g deadline <space><space><space> /by <time>
                //to ensure whitespaces alone as a description or time is not allowed
                if (description.strip().length() == 0) {
                    throw new IllegalDescriptionException("No description specified.");
                }
                //should not even come here as if time is pure whitespace, will be truncated
                if (time.length() == 0) {
                    throw new IllegalTimeException("No time specified.");
                }
                //strip leading get rid of white spaces infront, back alr dealt with
                time = time.stripLeading();
                return new DeadlineCommand(taskList, description, Parser.parseTime(time));
            } else if (lresponse.equals("event") || lresponse.startsWith("event ")) {
                if (response.length() < 7) {
                    throw new IllegalTokenException("No token found.");
                }
                response = response.substring(6);
                if (response.equals("/at") || response.startsWith("/at ")) {
                    throw new IllegalDescriptionException("No description specified.");
                }
                if (response.endsWith(" /at")) {
                    throw new IllegalTimeException("No time specified.");
                }
                int tokenPosition = response.indexOf(" /at ");
                if (tokenPosition < 0) {
                    throw new IllegalTokenException("No token found.");
                }
                String description = response.substring(0, tokenPosition);
                String time = response.substring(tokenPosition + 5);
                //to ensure whitespaces alone as a description or time is not allowed
                if (description.strip().length() == 0) {
                    throw new IllegalDescriptionException("No description specified.");
                }
                //should not even come here as if time is pure whitespace, will be truncated
                if (time.length() == 0) {
                    throw new IllegalTimeException("No time specified.");
                }
                //strip leading get rid of white spaces infront, back alr dealt with
                time = time.stripLeading();
                return new EventCommand(taskList, description, Parser.parseTime(time));
            }
        } catch (IllegalDescriptionException e) {
            return () -> "I'm sorry, but the description cannot be empty.";
        } catch (IllegalTimeException e) {
            return () -> "I'm sorry, but the time specified is either invalid or empty.";
        } catch (IllegalTokenException e) {
            return () -> "I'm sorry, but you are mising the \"/by\" or \"/at\" token.";
        }
        return () -> "I'm sorry, but I don't know what that means.";
    }

    /**
     * Parses string to the appropriate time it represents, if any.
     *
     * @param str the string to parse.
     * @return the LocalDate object representing time.
     * @throws IllegalTimeException If unable to parse the string to a valid time.
     */
    private static LocalDate parseTime(String str) throws IllegalTimeException {
        String sDay;
        String sMonth;
        String sYear;
        //ddmmyyyy
        String[] arr = new String[3];
        //forward slash, hyphen, dot, or whitespace, otherwise no separator cases
        if (str.indexOf('/') >= 0) {
            arr = Parser.parseTimeHelper('/', str);
        } else if (str.indexOf('-') >= 0) {
            arr = Parser.parseTimeHelper('-', str);
        } else if (str.indexOf('.') >= 0) {
            arr = Parser.parseTimeHelper('.', str);
        } else if (str.indexOf(' ') >= 0) {
            arr = Parser.parseTimeHelper(' ', str);
        } else if (str.length() == 4 || str.length() == 6 || str.length() == 8) {
            arr[0] = str.substring(0, 2);
            arr[1] = str.substring(2, 4);
            if (str.length() > 4) {
                arr[2] = str.substring(4);
            }
        }
        //if any of these if statements go through, arr will have atleast 2 no null elements
        //no method of parsing, arr[1] check redundant but just in case
        if (arr[0] == null || arr[1] == null) {
            throw new IllegalTimeException("Unable to parse due to invalid format.");
        }
        sDay = arr[0].strip();
        sMonth = arr[1].strip();
        //check if d/dd and m/mm/MMM
        if (sDay.length() < 1 || sDay.length() > 2 || sMonth.length() < 1 || sMonth.length() > 3) {
            throw new IllegalTimeException("Invalid day or month values.");
        }
        //convert month to number
        //length of 3 may indicate abbrev
        if (sMonth.length() == 3) {
            sMonth = Parser.months.get(sMonth.toLowerCase());
            if (sMonth == null) {
                throw new IllegalTimeException("Invalid abbreviated month.");
            }
        }
        //if year null, defaults to current year
        //else strip
        if (arr[2] == null) {
            sYear = "" + LocalDate.now().getYear();
        } else {
            sYear = arr[2].strip();
        }
        //check if yy/yyyy
        if (sYear.length() == 2) {
            sYear = "20" + sYear;
        } else if (sYear.length() != 4) {
            throw new IllegalTimeException("Invalid year input.");
        }
        try {
            return LocalDate.of(Integer.parseInt(sYear), Integer.parseInt(sMonth), Integer.parseInt(sDay));
        } catch (NumberFormatException | DateTimeException e) {
            //if cannot parse means invalid input
            throw new IllegalTimeException("Date input invalid.");
        }
    }

    /**
     * Splits a string by using a separator char.
     *
     * @param c the char used as separator.
     * @param str the string to split.
     * @return an array of parts of the input string in order.
     */
    private static String[] parseTimeHelper(char c, String str) {
        //helper that just splits string by specified char separator into array of size 3
        String[] arr = new String[3];
        int count = 0;
        for (int charIndex = str.indexOf(c); charIndex >= 0 && count < 2; charIndex = str.indexOf(c)) {
            arr[count++] = str.substring(0, charIndex);
            str = str.substring(charIndex + 1);
        }
        arr[count] = str;
        return arr;
    }
}
