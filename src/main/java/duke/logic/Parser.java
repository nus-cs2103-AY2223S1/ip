package duke.logic;

import duke.command.*;
import duke.exception.IllegalDescriptionException;
import duke.exception.IllegalTaskException;
import duke.exception.IllegalTimeException;
import duke.exception.IllegalTokenException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * Parser deals with making sense of user input.
 *
 * @author totsukatomofumi
 */
public class Parser {
    /** Task list to be passed into command constructors. */
    private TaskList taskList;

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
    public Runnable parse(String response) {
        //strip() to allow for any (unintentional) whitespaces before or after
        response = response.strip();

        if (response.equals("bye")) {   //bye command
            return new ByeCommand();
        } else if (response.equals("list")) {   //list command
            return new ListCommand(taskList);
        }

        try {
            if (response.equals("mark") || response.startsWith("mark ")) {  //mark command
                //no task specified, mark<space> is 5 char long
                if (response.length() < 6) {
                    throw new IllegalTaskException("No task specified.");
                }
                //taking index 5 inclusive onward
                //minus 1 to convert into index starting from 0
                int query = Integer.parseInt(response.substring(5).strip()) - 1;
                return new MarkCommand(taskList, query);    //will throw illegaltaskexception
            } else if (response.equals("unmark") || response.startsWith("unmark ")) {   //unmark command
                //no task specified, unmark<space> is 7 char long
                if (response.length() < 8) {
                    throw new IllegalTaskException("No task specified.");
                }
                //taking index 7 inclusive onward
                //minus 1 to convert into index starting from 0
                int query = Integer.parseInt(response.substring(7).strip()) - 1;
                return new UnmarkCommand(taskList, query);  //will throw illegaltaskexception
            } else if (response.equals("delete") || response.startsWith("delete ")) {   //delete command
                //no task specified, delete<space> is 7 char long
                if (response.length() < 8) {
                    throw new IllegalTaskException("No task specified.");
                }
                //taking index 7 inclusive onward
                //minus 1 to convert into index starting from 0
                int query = Integer.parseInt(response.substring(7).strip()) - 1;
                return new DeleteCommand(taskList, query);
            }
        } catch (NumberFormatException | IllegalTaskException e) {
            return () -> {
                System.out.println(" ☹ OOPS!!! The task number you have inputted does not exist or is invalid.");
            };
        }

        try {
            if (response.equals("todo") || response.startsWith("todo ")) {
                //completely nothing after command, todo<space> is 5 char long
                //todo command with whitespaces followed are not valid descriptions.
                if (response.length() < 6) {
                    throw new IllegalDescriptionException("No description specified.");
                }
                //taking index 5 onward as description
                //should be atleast 1 char by now
                String description = response.substring(5);
                return new ToDoCommand(taskList, description);
            } else if (response.equals("deadline") || response.startsWith("deadline ")) {
                //completely nothing after command, deadline<space> is 9 char long
                if (response.length() < 10) {
                    throw new IllegalTokenException("No token found.");
                }
                //cut away deadline<space>
                response = response.substring(9);
                if (response.equals("/by") || response.startsWith("/by ")) {    //e.g. deadline /by <time>
                    throw new IllegalDescriptionException("No description specified."); //or deadline /by
                }
                if (response.endsWith(" /by")) {    //e.g. deadline <desc> /by
                    throw new IllegalTimeException("No time specified.");
                }
                int tokenPosition = response.indexOf(" /by ");
                if (tokenPosition < 0) {
                    throw new IllegalTokenException("No token found.");
                }
                String description = response.substring(0, tokenPosition);
                String time = response.substring(tokenPosition + 5);    //starting from after /by<space>
                //as of now still can have e.g deadline <space><space><space> /by <time>
                //to ensure whitespaces alone as a description or time is not allowed
                if (description.strip().length() == 0) {
                    throw new IllegalDescriptionException("No description specified.");
                }
                if (time.length() == 0) {   //should not even come here as if time is pure whitespace, will be truncated
                    throw new IllegalTimeException("No time specified.");
                }
                //strip leading get rid of white spaces infront, back alr dealt with
                time = time.stripLeading();
                return new DeadlineCommand(taskList, description, Parser.parseTime(time));
            } else if (response.equals("event") || response.startsWith("event ")) {
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
                if (time.length() == 0) {   //should not even come here as if time is pure whitespace, will be truncated
                    throw new IllegalTimeException("No time specified.");
                }
                //strip leading get rid of white spaces infront, back alr dealt with
                time = time.stripLeading();
                return new EventCommand(taskList, description, Parser.parseTime(time));
            }
        } catch (IllegalDescriptionException e) {
            return () -> {
                System.out.println(" ☹ OOPS!!! The description cannot be empty.");
            };
        } catch (IllegalTimeException e) {
            return () -> {
                System.out.println(" ☹ OOPS!!! The time specified is either invalid or empty.");
            };
        } catch (IllegalTokenException e) {
            return () -> {
                System.out.println(" ☹ OOPS!!! You are mising the \"/by\" or \"/at\" token.");
            };
        }
        return () -> {
            System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means. :-(");
        };
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
        }   //if any of these if statements go through, arr will have atleast 2 no null elements
        if (arr[0] == null || arr[1] == null) { //no method of parsing, arr[1] check redundant but just in case
            throw new IllegalTimeException("Unable to parse due to invalid format.");
        }
        sDay = arr[0].strip();
        sMonth = arr[1].strip();
        //check if d/dd and m/mm/MMM
        if (sDay.length() < 1 || sDay.length() > 2 || sMonth.length() < 1 || sMonth.length() > 3) {
            throw new IllegalTimeException("Invalid day or month values.");
        }
        //convert month to number
        if (sMonth.length() == 3) {  //length of 3 may indicate abbrev
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
