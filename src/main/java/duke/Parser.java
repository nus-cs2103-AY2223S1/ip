package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.LoadCommand;
import duke.command.MarkCommand;
import duke.command.MatchCommand;
import duke.command.SaveCommand;
import duke.command.UnmarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    /**
     * Parses the user command and returns the corresponding command object.
     *
     * @param command The user command
     * @return The corresponding command object
     * @throws DukeException if the command is invalid
     */
    public static Command parse(String command) throws DukeException {
        switch (command) {
        case "b":
        case "bye":
            return new ExitCommand();
        case "ls":
        case "list":
            return new ListCommand();
        case "m":
        case "mark":
            return new MarkCommand();
        case "u":
        case "unmark":
            return new UnmarkCommand();
        case "dl":
        case "deadline":
        case "t":
        case "todo":
        case "e":
        case "event":
            return new AddCommand();
        case "d":
        case "delete":
            return new DeleteCommand();
        case "s":
        case "save":
            return new SaveCommand();
        case "ld":
        case "load":
            return new LoadCommand();
        case "date":
            return new MatchCommand();
        case "f":
        case "find":
            return new FindCommand();
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Finds the String between first command and second command (if exist).
     *
     * @param input User input
     * @param command First command
     * @return String between first command and second command (if exist)
     * @throws DukeException if the command is invalid
     */
    public static String findFirstCommand(String input, String command) throws DukeException {
        assert input != null : "Input cannot be null";
        assert command != null : "Command cannot be null";
        int endOfCommand = input.indexOf("/");
        int beginIndex = input.indexOf(command) + command.length() + 1;
        if (beginIndex > input.length()) {
            throw new DukeException("The description of a " + command + " cannot be empty.");
        }
        return endOfCommand == -1
                ? input.substring(beginIndex)
                : input.substring(beginIndex, endOfCommand);
    }

    /**
     * Finds the String between second command (if exist) and end.
     *
     * @param input User input
     * @param command Second command
     * @return String between second command (if exist) and end
     */
    public static String findSecondCommand(String input, String command) {
        assert input != null : "Input cannot be null";
        assert command != null : "Command cannot be null";
        return input.contains(command) && !command.equals("")
                ? input.substring(input.indexOf(command) + command.length() + 1)
                : "";
    }

    /**
     * Parses the user input and creates a Task object.
     *
     * @param input User input
     * @param command Command that the user inputs
     * @param storageList StorageList object
     * @param secCommand  Second command that the user inputs
     */
    public static void parseTask(String input, String command, StorageList storageList, String secCommand) {
        assert input != null : "Input cannot be null";
        assert command != null : "Command cannot be null";
        try {
            String desc = findFirstCommand(input, command);
            String secondCommand = findSecondCommand(input, secCommand);
            switch (command) {
            case "dl":
            case "deadline":
                storageList.addTask(new Deadline(desc, secondCommand));
                break;
            case "e":
            case "event":
                storageList.addTask(new Event(desc, secondCommand));
                break;
            case "t":
            case "todo":
                storageList.addTask(new Todo(desc));
                break;
            default:
            }
            FileManager.save(storageList, "./default.txt");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Finds the index of the Task that the user wants to mark/unmark/delete.
     *
     * @param input User input
     * @return Index of the Task
     */
    public static int getIndex(String input) {
        assert input != null : "Input cannot be null";
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }
}
