import java.io.IOException;
import java.util.Scanner;

public class Parser {

//    public static void bye() {
//        System.out.println("Bye. Hope to see you again soon!");
//        System.exit(0);
//    }

    public static Command parseTodo(String desc) throws DukeException {
        Scanner sc = new Scanner(desc);
        if (!sc.hasNext()) {
            throw new DukeException("OOPS!! ToDo description should not be empty!");
        } else {
            return new AddCommand(desc, false);
        }
    }

    public static Command parseDeadline(String desc) throws DukeException {
        Scanner scanner = new Scanner(desc);
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
            throw new DukeException("OOPS!! Please enter a valid task number to mark!");
        } else {
            int taskNo = scanner.nextInt();
            return new MarkAsDoneCommand(taskNo);
        }
    }

    public static Command parseMarkAsUndone(String desc) throws DukeException{
        Scanner scanner = new Scanner(desc);
        if (!scanner.hasNextInt()) {
            throw new DukeException("OOPS!! Please enter a valid task number to mark!");
        } else {
            int taskNo = scanner.nextInt();
            return new MarkAsUndoneCommand(taskNo);
        }
    }

    public static Command parseDelete(String desc) throws DukeException {
        Scanner scanner = new Scanner(desc);
        if (!scanner.hasNextInt()) {
            throw new DukeException("OOPS!! Please enter a valid task number to mark!");
        } else {
            int taskNo = scanner.nextInt();
            return new DeleteCommand(taskNo);
        }
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

            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static Command parseFileLine(String desc) throws DukeException {
        String[] words = desc.split("\\s\\|\\s");
        int size = words.length;
        if (size == 1) return new NullCommand();
        String typeOfTask = words[0];
        boolean isDone = words[1] == "X" ? true : false;
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
                throw new DukeException("File may be corrupted");
        }
    }
}

