package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.command.*;


/**
 * Parser class to parse texts into commands.
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class Parser {

    public static Command parseTodo(String desc) {
        Scanner sc = new Scanner(desc);
        if (!sc.hasNext()) {
            return new ResponseCommand("OOPS!! ToDo description should not be empty!");
        }
        return new AddToDoCommand(desc, false);
    }

    public static Command parseDeadline(String desc) {
        Scanner scanner = new Scanner(desc);
        if (!scanner.hasNext()) {
            return new ResponseCommand("OOPS!! Deadline description should not be empty!");
        }
        if (!desc.contains("/by")) {
            return new ResponseCommand("OOPS!! Please enter a date for the deadline. Enter \"help\" for more info.");
        }
        String description = "";
        String date;
        while (!scanner.hasNext("/by")) {
            description += scanner.next();
        }
        scanner.next(); //skips "/by"
        date = scanner.nextLine();
        return new AddDeadlineCommand(description, false, date);
    }

    public static Command parseEvent(String desc) {
        Scanner scanner = new Scanner(desc);
        if (!scanner.hasNext()) {
            return new ResponseCommand("OOPS!! Event description should not be empty!");
        }
        if (!desc.contains("/at")) {
            return new ResponseCommand("OOPS!! Please enter a date for the event. Enter \"help\" for more info.");
        }
        String description = "";
        String date;
        while (!scanner.hasNext("/at")) {
            description += scanner.next();
        }
        scanner.next(); //skips "/at"
        date = scanner.nextLine();
        return new AddEventCommand(description, false, date);
    }

    public static Command parseMarkAsDone(String desc) {
        String[] taskNosString = desc.split("\\s*,\\s*");
        if (taskNosString[0].equals("")) {
            return new ResponseCommand("OOPS!! Please enter the task number(s) you want to mark!");
        }
        int[] taskNosInt = new int[taskNosString.length];
        int currIndex = 0;
        for (String string : taskNosString) {
            int taskNo;
            try {
                taskNo = Integer.parseInt(string) - 1;
            } catch (NumberFormatException ne) {
                return new ResponseCommand("OOPS!! Please enter a valid task number. " +
                        "To unmark multiple tasks, add a comma between the task numbers.");
            }
            if (taskNo < 0 || taskNo >= Task.getTaskCount()) {
                return new ResponseCommand("Task number " + (taskNo + 1) + " does not exist.");
            }
            taskNosInt[currIndex] = taskNo;
            currIndex++;
        }
        return new MarkAsDoneCommand(taskNosInt);
    }

    public static Command parseMarkAsUndone(String desc) {
        String[] taskNosString = desc.split("\\s*,\\s*");
        if (taskNosString[0].equals("")) {
            return new ResponseCommand("OOPS!! Please enter the task number(s) you want to unmark!");
        }
        int[] taskNosInt = new int[taskNosString.length];
        int currIndex = 0;
        for (String string : taskNosString) {
            int taskNo;
            try {
                taskNo = Integer.parseInt(string) - 1;
            } catch (NumberFormatException ne) {
                return new ResponseCommand("OOPS!! Please enter a valid task number. " +
                        "To mark multiple tasks, add a comma between the task numbers.");
            }
            if (taskNo < 0 || taskNo >= Task.getTaskCount()) {
                return new ResponseCommand("OOPS!! Task number " + (taskNo + 1) + " does not exist.");
            }
            taskNosInt[currIndex] = taskNo;
            currIndex++;
        }
        return new MarkAsUndoneCommand(taskNosInt);

    }

    public static Command parseDelete(String desc) {
        String[] taskNosString = desc.split(",");
        if (taskNosString[0].equals("")) {
            return new ResponseCommand("OOPS!! Please enter the task number you would like to delete!");
        }
        int[] taskNosInt = new int[taskNosString.length];
        int currIndex = 0;
        for (String string : taskNosString) {
            int taskNo;
            try {
                taskNo = Integer.parseInt(string) - 1;
            } catch (NumberFormatException ne) {
                return new ResponseCommand("OOPS!! Please enter a valid task number. " +
                        "To mark multiple tasks, add a comma between the task numbers.");
            }
            System.out.println(taskNo);
            if (taskNo < 0 || taskNo >= Task.getTaskCount()) {
                return new ResponseCommand("OOPS!! Task number " + (taskNo + 1) + " does not exist.");
            }
            taskNosInt[currIndex] = taskNo;
            currIndex++;
        }
        return new DeleteCommand(taskNosInt);
    }

    public static Command parseFind(String desc) {
        return new FindCommand(desc);
    }

    public static String parseDate(String date) {
        LocalDate localDate = LocalDate.parse(date.trim());
        return localDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    public static Command parseUpdate(String desc) {
        Scanner sc = new Scanner(desc);
        String field = sc.next();
        int taskNo = Integer.parseInt(sc.next()) - 1;
        sc.next();
        String updatedField = sc.nextLine();

        switch (field) {
            case "description":
                return new UpdateTaskDescriptionCommand(taskNo, updatedField);

                // more additions can be made (e.g. update date for deadlines and events)

            default:
                return new InvalidCommand();
        }
    }


    public static Command parse(String rawCommand) throws DukeException, AssertionError {
        UI.userInput(rawCommand);
        Scanner sc = new Scanner(rawCommand);
        String first = sc.next();
        String desc = "";
        if (sc.hasNext()) {
            desc = sc.nextLine().trim();
        }
        try {
            switch (first) {
                case ("help"):
                    return new HelpCommand();

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
                    assert desc.length() != 0 : "Cannot mark an empty task!";
                    return Parser.parseMarkAsDone(desc);

                case ("unmark"):
                    assert desc.length() != 0 : "Cannot unmark an empty task!";
                    return Parser.parseMarkAsUndone(desc);

                case ("delete"):
                    assert desc.length() != 0 : "Cannot delete an empty task!";
                    return Parser.parseDelete(desc);

                case ("find"):
                    assert desc.length() != 0 : "Cannot find an empty string!";
                    return Parser.parseFind(desc);

                case ("update"):
                    assert desc.length() != 0 : "Cannot find an empty string!";
                    return Parser.parseUpdate(desc);

                default:
                    return new InvalidCommand();
            }
        } catch (AssertionError ae) {
            return new ResponseCommand("OOPS!! " + ae.getMessage());
        }

    }

    public static Command parseFileLine(String desc) {
        String[] words = desc.split("\\s\\|\\s");
        int size = words.length;
        if (size == 1) return new NullCommand();
        String typeOfTask = words[0];
        boolean isDone = words[1].equals("X");
        String description = words[2];
        String date;

        switch (typeOfTask) {
            case ("T"):
                return new AddToDoCommand(description, isDone);
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

