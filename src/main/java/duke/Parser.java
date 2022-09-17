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

    /**
     * Returns an AddToDoCommand.
     *
     * @param desc description
     * @return Command
     */

    public static Command parseTodo(String desc) {
        Scanner sc = new Scanner(desc);
        if (!sc.hasNext()) {
            return new ResponseCommand("OOPS!! ToDo description should not be empty!");
        }
        return new AddToDoCommand(desc, false);
    }

    /**
     * Returns an AddDeadlineCommand.
     *
     * @param desc description
     * @return Command
     */

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
        scanner.next();
        if (!scanner.hasNext()) {
            return new ResponseCommand("OOPS!! Please enter a deadline for the task. ");
        }
        date = scanner.nextLine();
        return new AddDeadlineCommand(description, false, date);
    }

    /**
     * Returns an AddEventCommand.
     *
     * @param desc description
     * @return Command
     */

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
            description += " " + scanner.next();
        }
        scanner.next();
        if (!scanner.hasNext()) {
            return new ResponseCommand("OOPS!! Please enter a date for the event. ");
        }
        date = scanner.nextLine();
        return new AddEventCommand(description, false, date);
    }

    /**
     * Returns a MarkAsDoneCommand.
     *
     * @param desc description
     * @return Command
     */

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

    /**
     * Returns a MarkAsUndoneCommand.
     *
     * @param desc description
     * @return Command
     */

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

    /**
     * Returns a DeleteCommand.
     *
     * @param desc description
     * @return Command
     */

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
                taskNo = Integer.parseInt(string.trim()) - 1;
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

    /**
     * Returns a FindCommand.
     *
     * @param desc description
     * @return Command
     */

    public static Command parseFind(String desc) {
        return new FindCommand(desc);
    }

    /**
     * Returns a String containing the formatted date.
     *
     * @param date String
     * @return Command
     */

    public static String parseDate(String date) {
        LocalDate localDate = LocalDate.parse(date.trim());
        return localDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Returns an Update*Command.
     *
     * @param desc description
     * @return Command
     */

    public static Command parseUpdate(String desc) {
        Scanner sc = new Scanner(desc);
        String field = sc.next();
        int taskNo = Integer.parseInt(sc.next()) - 1;
        sc.next();
        String updatedField = sc.nextLine();

        switch (field) {
            case "desc":
                return new UpdateTaskDescriptionCommand(taskNo, updatedField);

                // more additions can be made (e.g. update date for deadlines and events)

            default:
                return new InvalidCommand();
        }
    }

    /**
     * Parses the first word from user input and returns a command.
     *
     * @param rawCommand String
     * @return Command
     * @throws DukeException when input is invalid
     * @throws AssertionError when input is invalid
     */

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

    /**
     * Parses the duke.txt file whenever Duke program is started
     *
     * @param desc String
     * @return Command
     */

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

