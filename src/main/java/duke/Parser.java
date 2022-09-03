package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import duke.command.*;
import org.yaml.snakeyaml.util.ArrayUtils;

/**
 * Parser class to parse texts into commands.
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class Parser {

    public static Command parseTodo(String desc) throws DukeException {
        Scanner sc = new Scanner(desc);
        if (!sc.hasNext()) {
            return new ResponseCommand("OOPS!! ToDo description should not be empty!");
        }
        return new AddCommand(desc, false);
    }

    public static Command parseDeadline(String desc) throws DukeException {
        Scanner scanner = new Scanner(desc);
        if (!scanner.hasNext()) {
            return new ResponseCommand("OOPS!! Deadline description should not be empty!");
        }
        String description = "";
        String date = "";
        while (!scanner.hasNext("/by")) {
            description += scanner.next();
        }
        scanner.next(); //skips "/by"
        date = scanner.nextLine();
        return new AddDeadlineCommand(description, false, date);
    }

    public static Command parseEvent(String desc) throws DukeException {
        Scanner scanner = new Scanner(desc);
        if (!scanner.hasNext()) {
            return new ResponseCommand("OOPS!! Event description should not be empty!");
        }
        String description = "";
        String date = "";
        while (!scanner.hasNext("/at")) {
            description += scanner.next();
        }
        scanner.next(); //skips "/at"
        date = scanner.nextLine();
        return new AddEventCommand(description, false, date);
    }

    public static Command parseMarkAsDone(String desc) throws DukeException{
        Scanner scanner = new Scanner(desc);
        if (!scanner.hasNextInt()) {
            return new ResponseCommand("OOPS!! Please enter a valid task number to mark!");
        }
        int taskNo = scanner.nextInt() - 1;
        if (taskNo < 0 || taskNo >= Task.getTaskCount()) {
            return new ResponseCommand("OOPS!! Please enter a valid task number to mark!");
        }
        return new MarkAsDoneCommand(taskNo);
    }

    public static Command parseMarkAsUndone(String desc) throws DukeException{
        Scanner scanner = new Scanner(desc);
        if (!scanner.hasNextInt()) {
            return new ResponseCommand("OOPS!! Please enter a valid task number to unmark!");
        }
        int taskNo = scanner.nextInt() - 1;
        if (taskNo < 0 || taskNo >= Task.getTaskCount()) {
            return new ResponseCommand("OOPS!! Please enter a valid task number to unmark!");
        }
        return new MarkAsUndoneCommand(taskNo);

    }

    public static Command parseDelete(String desc) {
        Scanner scanner = new Scanner(desc);
        if (!scanner.hasNextInt()) {
            return new ResponseCommand("OOPS!! Please enter the task number you would like to delete!");
        }
        int taskNo = scanner.nextInt() - 1;
        if (taskNo < 0 || taskNo >= Task.getTaskCount()) {
            return new ResponseCommand("OOPS!! Task number " + taskNo + "does not exist.");
        }
        return new DeleteCommand(taskNo);
    }

    public static Command parseFind(String desc) {
        return new FindCommand(desc);
    }

    public static String parseDate(String date) {
        LocalDate localDate = LocalDate.parse(date.trim());
        String formatDate = localDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return formatDate;
    }

    public static Command parse(String rawCommand) throws DukeException {
        Scanner sc = new Scanner(rawCommand);
        String first = sc.next();
        String desc = "";
        if (sc.hasNext()) {
            desc = sc.nextLine().trim();
        }

        switch (first) {
            case ("bye"):
                return new ByeCommand();

            case ("list"):
                return new ListCommand();

            case ("todo"):
                return Parser.parseTodo(desc);

            case ("deadline"):
                return Parser.parseDeadline(desc);

            case ("event"):
                return Parser.parseEvent(desc);

            case ("mark"):
                return Parser.parseMarkAsDone(desc);

            case ("unmark"):
                return Parser.parseMarkAsUndone(desc);

            case ("delete"):
                return Parser.parseDelete(desc);

            case ("find") :
                return Parser.parseFind(desc);

            default:
                return new InvalidCommand();
        }
    }

    public static Command parseFileLine(String desc) throws DukeException {
        String[] words = desc.split("\\s\\|\\s");
        int size = words.length;
        if (size == 1) return new NullCommand();
        String typeOfTask = words[0];
        boolean isDone = words[1].equals("X");
        String description = words[2];
        String date = " ";

        switch (typeOfTask) {
            case ("T"):
                return new AddCommand(description, isDone);
            case ("D"):
                date = words[3];
                return new AddDeadlineCommand(description, isDone, date);
            case ("E"):
                date = words[3];
                return new AddEventCommand(description, isDone, date);
            default :
                return new ResponseCommand("OOPS!!! File may be corrupted, please delete file and try again!");
        }
    }
}

