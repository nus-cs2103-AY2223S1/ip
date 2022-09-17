package justin;

import justin.command.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Represents a class that is responsible for
 * reading data from inputs or from text files.
 * @author Justin Cheng.
 */
public class Parser {
    private static Scanner sc;

    /**
     * Returns an AddToDoCommand through scanning the description.
     * @param message The description of the command.
     * @return An AddToDoCommand.
     * @throws DukeException If the description of the ToDo is empty.
     */
    public static Command parseToDo(String message) throws DukeException{
        sc = new Scanner(message);
        if (sc.hasNext()) {
            String description = sc.nextLine();
            String[] strArr = splitArray(description);
            return new AddToDoCommand(false, strArr);
        } else {
            throw new DukeException("The description of the todo should not be empty!");
        }
    }

    /**
     * Returns an AddDeadlineCommand through scanning the description.
     * @param message The description of the command.
     * @return An AddDeadlineCommand.
     * @throws DukeException If the message given is invalid.
     */
    public static Command parseDeadline(String message) throws DukeException {
        sc = new Scanner(message);
        try {
            String description = "";
            while (!sc.hasNext("/by")) {
                String next = sc.next();
                description += next + " ";
            }
            description = description.substring(0, description.length() - 1);
            sc.next();
            String next = sc.nextLine();
            String[] date = next.split(" ");
            return new AddDeadlineCommand(description, date);
        } catch (NoSuchElementException e) {
            throw new DukeException("Please enter a valid message!\n" +
                    "Format of the message should be:\n\n" +
                    "deadline <task> /by <YYYY-MM-DD> <HH:MM>");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid message!\n" +
                    "Format of the message should be:\n\n" +
                    "deadline <task> /by <YYYY-MM-DD> <HH:MM>");
        }
    }

    /**
     * Returns an AddEventCommand through scanning the description.
     * @param message The description of the command.
     * @return An AddEventCommand.
     * @throws DukeException If the message is invalid.
     */
    public static Command parseEvent(String message) throws DukeException {
        sc = new Scanner(message);
        try {
            String description = "";
            while (!sc.hasNext("/at")) {
                String next = sc.next();
                description += next + " ";
            }
            description = description.substring(0, description.length() - 1);
            sc.next();
            String next = sc.nextLine();
            String[] date = next.split(" ");
            return new AddEventCommand(description, date);
        } catch (NoSuchElementException e) {
            throw new DukeException("Please enter a valid message!\n" +
                    "Format of the message should be:\n\n" +
                    "event <task> /at <YYYY-MM-DD> <HH:MM>");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid message!\n" +
                    "Format of the message should be:\n\n" +
                    "event <task> /at <YYYY-MM-DD> <HH:MM>");
        }
    }


    /**
     * Returns a FindCommand through the scanning the keywords.
     * @param message The keywords in String to find matching tasks.
     * @return A FindCommand.
     * @throws DukeException If the input is invalid.
     */
    public static Command parseFind(String message) throws DukeException {
        sc = new Scanner(message);
        String res = "";
        try {
            while (sc.hasNext()) {
                res += sc.next() + " ";
            }
            res = res.substring(0, res.length() - 1);
        } catch (StringIndexOutOfBoundsException e){
            throw new DukeException("Please type in something for me to find!");
        }
        return new FindCommand(res);
    }

    /**
     * Returns a command from scanning the inputs of users.
     * @param message The description of the command.
     * @return A Command.
     * @throws DukeException If the input is invalid.
     */
    public static Command parse(String message) throws DukeException {
        sc = new Scanner(message);
        String first = sc.next();
        try {
            switch (first) {
                case "bye": {
                    return new ExitCommand();
                }
                case "list": {
                    return new ListCommand();
                }
                case "todo": {
                    String description = sc.nextLine();
                    return parseToDo(description);
                }
                case "deadline": {
                    String description = sc.nextLine();
                    return parseDeadline(description);
                }
                case "event": {
                    String description = sc.nextLine();
                    return parseEvent(description);
                }
                case "mark": {
                    String description = sc.nextLine();
                    return new MarkCommand(splitArray(description));
                }
                case "unmark": {
                    String description = sc.nextLine();
                    return new UnmarkCommand(splitArray(description));
                }
                case "delete": {
                    String description = sc.nextLine();
                    return new DeleteCommand(splitArray(description));
                }
                case "find": {
                    String description = sc.nextLine();
                    return parseFind(description);
                }
                case "help": {
                    return new HelpCommand();
                }
                default: {
                    throw new DukeException("OOPS! Sorry I do not know what that means...");
                }
            }
        } catch (NoSuchElementException e) {
            throw new DukeException("Please type something!");
        }
    }

    /**
     * Returns a Command through scanning the text file in String format.
     * @param message The description of the command.
     * @return A Command.
     * @throws DukeException If the file has been corrupted.
     */
    public static Command parseCommand(String message) throws DukeException {
        String[] arr = message.split("\\s\\|\\s");
        try {
            assert arr.length > 1;
        } catch (AssertionError e) {
            return new NewCommand();
        }
        String task = arr[0];
        boolean isDone = arr[1].equals("Done!") ? true : false;
        String description = arr[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hhmma");
        LocalDate ld = null;
        LocalTime lt = null;
        try {
            switch (task) {
                case "T":
                    return new AddToDoCommand(isDone, description);
                case "D":
                    ld = LocalDate.parse(arr[3], formatter);
                    lt = LocalTime.parse(arr[3], formatter);
                    return new AddDeadlineCommand(description, isDone, ld.toString(), lt.toString());
                case "E":
                    ld = LocalDate.parse(arr[3], formatter);
                    lt = LocalTime.parse(arr[3], formatter);
                    return new AddEventCommand(description, isDone, ld.toString(), lt.toString());
                default:
                    throw new DukeException("OOPS, looks like the file has been corrupted. Please delete the file and try again.");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Dates and times have been corrupted in the file. Please delete the file and try again.");
        }
    }

    /**
     * Splits the array by commas.
     * @param description The array that comes with commas.
     * @return An array split by commas.
     */
    public static String[] splitArray(String description) throws DukeException {
        String[] res = description.split(",");
        try {
            for (int i = 0; i < res.length; i++) {
                if (res[i].charAt(res[i].length() - 1) == ' ') { //if the last character is a space
                    res[i] = res[i].substring(0, res[i].length() - 1);
                }
                if (res[i].charAt(0) == ' ') {
                    res[i] = res[i].substring(1);
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please write in a proper format!");
        }
        return res;
    }
}
