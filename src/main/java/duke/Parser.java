package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.NewCommand;
import duke.command.UnmarkCommand;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Represents a class that is responsible for
 * reading data from inputs or from text files.
 * @author Justin Cheng.
 */
public class Parser {

    /**
     * Returns an AddToDoCommand through scanning the description.
     * @param message The description of the command.
     * @return an AddToDoCommand.
     * @throws DukeException if the description of the ToDo is empty.
     */
    public static Command parseToDo(String message) throws DukeException{
        Scanner sc = new Scanner(message);
        if (sc.hasNext()) {
            String description = sc.nextLine();
            return new AddToDoCommand(description, false);
        } else {
            throw new DukeException("OOPS! The description of the todo should not be empty!");
        }
    }

    /**
     * Returns an AddDeadlineCommand through scanning the description.
     * @param message The description of the command.
     * @return an AddDeadlineCommand.
     * @throws DukeException if the message given is invalid.
     */
    public static Command parseDeadline(String message) throws DukeException {
        Scanner sc = new Scanner(message);
        try {
            String description = "";
            while (!sc.hasNext("/by")) {
                String next = sc.next();
                description += next + " ";
            }
            sc.next();
            String next = sc.nextLine();
            String[] date = next.split(" ");
            return new AddDeadlineCommand(description, date);
        } catch (Exception e) {
            throw new DukeException("OOPS!! Please enter a valid message!");
        }
    }

    /**
     * Returns an AddEventCommand through scanning the description.
     * @param message The description of the command.
     * @return an AddEventCommand.
     * @throws DukeException if the message is invalid.
     */
    public static Command parseEvent(String message) throws DukeException {
        Scanner sc = new Scanner(message);
        try {
            String description = "";
            while (!sc.hasNext("/at")) {
                String next = sc.next();
                description += next + " ";
            }
            sc.next();
            String next = sc.nextLine();
            String[] date = next.split(" ");
            return new AddEventCommand(description, date);
        } catch (Exception e) {
            throw new DukeException("OOPS! Please enter a valid message!");
        }
    }


    public static Command parseFind(String message) {
        Scanner sc = new Scanner(message);
        String res = "";
        while (sc.hasNext()) {
            res += sc.next() + " ";
        }
        res = res.substring(0, res.length() - 1);
        return new FindCommand(res);
    }

    /**
     * Returns a command from scanning the inputs of users.
     * @param message The description of the command.
     * @return a Command.
     * @throws DukeException if the input is invalid.
     */
    public static Command parse(String message) throws DukeException {
        Scanner sc = new Scanner(message);
        String first = sc.next();
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
                int num = sc.nextInt();
                return new MarkCommand(num);
            }
            case "unmark": {
                int num = sc.nextInt();
                return new UnmarkCommand(num);
            }
            case "delete": {
                int num = sc.nextInt();
                return new DeleteCommand(num);
            }
            case "find": {
                String description = sc.nextLine();
                return parseFind(description);
            }
            default: {
                throw new DukeException("OOPS! Sorry I do not know what that means...");
            }
        }
    }

    /**
     * Returns a Command through scanning the text file in String format.
     * @param message The description of the command.
     * @return A Command.
     * @throws DukeException if the file has been corrupted.
     */
    public static Command parseCommand(String message) throws DukeException {
        String[] arr = message.split("\\s\\|\\s");
        if (arr.length == 1) {
            return new NewCommand();
        }
        String task = arr[0];
        boolean isDone = arr[1] == "X" ? true : false;
        String description = arr[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hhmma");
        LocalDate ld = null;
        LocalTime lt = null;
        switch (task) {
            case "T":
                return new AddToDoCommand(description, isDone);
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
    }
}
