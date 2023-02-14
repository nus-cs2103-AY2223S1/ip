package parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Import Commands
import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.UpdateCommand;
// Import Luna Exceptions
import exception.LunaException;
import exception.LunaInvalidCommandException;
import exception.LunaInvalidDateException;
import exception.LunaInvalidDescriptionException;
import exception.LunaInvalidFormatException;
import exception.LunaInvalidIndexException;
// Import Tasks
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

/**
 * Deals with making sense of the user command.
 *
 * @author fannyjian
 */
public class Parser {

    /**
     * Takes in a string of user command and creates a new instance of Command
     * according to the keyword.
     *
     * @param cmd String of command to process.
     * @return Command corresponding to the user's instruction.
     * @throws LunaException.
     */
    public static Command parse(String cmd) throws LunaException {
        String[] cmdSplit = cmd.split(" ", 2);
        Command command;
        switch (cmdSplit[0]) {
        case "todo":
            if (cmd.length() <= 5) {
                throw new LunaInvalidDescriptionException();
            }
            command = new AddCommand(cmdSplit[1]);
            break;
        case "deadline":
            if (cmd.length() <= 9) {
                throw new LunaInvalidDescriptionException();
            }
            if (!cmdSplit[1].contains(" /by ")) {
                throw new LunaInvalidFormatException();
            }
            String[] desSplit = cmdSplit[1].split(" /by ");
            command = new AddCommand("deadline", desSplit[0], parseDate(desSplit[1]));
            break;
        case "event":
            if (cmd.length() <= 6) {
                throw new LunaInvalidDescriptionException();
            }
            if (!cmdSplit[1].contains(" /at ")) {
                throw new LunaInvalidFormatException();
            }
            String[] split = cmdSplit[1].split(" /at ");
            command = new AddCommand("event", split[0], parseDate(split[1]));
            break;
        case "list":
            command = new ListCommand();
            break;
        case "bye":
            command = new ExitCommand();
            break;
        case "delete":
            command = new DeleteCommand(parseNum(cmdSplit[1]));
            break;
        case "mark":
            command = new UpdateCommand("mark", parseNum(cmdSplit[1]));
            break;
        case "unmark":
            command = new UpdateCommand("unmark", parseNum(cmdSplit[1]));
            break;
        case "find":
            command = new FindCommand(cmdSplit[1].toLowerCase());
            break;
        default:
            throw new LunaInvalidCommandException();
        }
        return command;
    }

    /**
     * Processes lines of text from the hard disk by converting strings of tasks into
     * appropriate Task instances.
     *
     * @param tasks String of task to be converted to a Task instance
     * @return A Task instance.
     */
    public static Task parseSaved(String tasks) {
        String text = tasks.substring(7);
        if (text.startsWith("[T]")) {
            // Abstract task description
            String[] sp = text.split("] ");

            // Create new Todo
            Task task = new Todo(sp[1]);

            // Update status
            if (sp[0].substring(3).equals("[✧")) {
                task.setStatusIcon(true);
            }
            return task;
        } else if (text.startsWith("[D]")) {
            // Abstract task description and date
            String[] sp = text.split("] ");
            String [] desSplit = sp[1].split(" BY ");
            String des = desSplit[0];
            String by = desSplit[1].substring(0, 11);

            // Create date
            LocalDate date = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd MMM yyyy"));

            // Create new Deadline
            Task task = new Deadline(des, date);

            // Update status
            if (sp[0].substring(3).equals("[✧")) {
                task.setStatusIcon(true);
            }
            return task;
        } else if (text.startsWith("[E]")) {
            // Abstract task description and date
            String[] sp = text.split("] ");
            String [] desSplit = sp[1].split(" AT ");
            String des = desSplit[0];
            String at = desSplit[1].substring(0, 11);

            // Create date
            LocalDate date = LocalDate.parse(at, DateTimeFormatter.ofPattern("dd MMM yyyy"));

            // Create new Event
            Task task = new Event(des, date);

            // Update status
            if (sp[0].substring(3).equals("[✧")) {
                task.setStatusIcon(true);
            }
            return task;
        } else {
            return null;
        }
    }

    /**
     * Checks if input string represents a valid number index and converts it to an integer.
     *
     * @param txt String to be checked.
     * @return A number index.
     * @throws LunaException.
     */
    public static int parseNum(String txt) throws LunaException {
        int num;
        try {
            num = Integer.parseInt(txt);
            if (num > TaskList.size()) {
                throw new LunaInvalidIndexException();
            }
        } catch (NumberFormatException e) {
            throw new LunaInvalidIndexException();
        }
        return num;
    }

    /**
     * Checks if input string represents a valid date in the correct format
     * and converts it to a LocalDate instance.
     *
     * @param txt String to be checked.
     * @return A LocalDate in dd-MMM-yyyy format.
     * @throws LunaException.
     */
    public static LocalDate parseDate(String txt) throws LunaException {
        try {
            return LocalDate.parse(txt, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new LunaInvalidDateException();
        }
    }
}
