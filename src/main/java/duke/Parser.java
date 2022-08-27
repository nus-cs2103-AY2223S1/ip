package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.NewCommand;
import duke.command.UnmarkCommand;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Parser {

    public static Command parseToDo(String message) throws DukeException{
        Scanner sc = new Scanner(message);
        if (sc.hasNext()) {
            String description = sc.nextLine();
            return new AddToDoCommand(description, false);
        } else {
            throw new DukeException("OOPS! The description of the todo should not be empty!");
        }
    }

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
            default: {
                throw new DukeException("OOPS! Sorry I do not know what that means...");
            }
        }
    }

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
