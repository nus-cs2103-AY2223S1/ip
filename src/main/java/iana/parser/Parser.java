package iana.parser;

// import iana.command.Actions;
import iana.command.AddTaskCommand;
import iana.command.AnotherCommand;
import iana.command.Command;
import iana.command.DeleteCommand;
import iana.command.ExitCommand;
import iana.command.FindCommand;
import iana.command.HelpCommand;
import iana.command.ListCommand;
import iana.command.MarkCommand;
import iana.command.UnmarkCommand;
import iana.exception.IanaException;

/**
 * Parser that takes in command line arguments.
 */
public class Parser {

    /**
     * Parses full user input.
     * 
     * @param input user's command line input.
     * @return command that matches user's input.
     * @throws IanaException if user's input does not match correct format.
     */
    public static Command parse(String input) throws IanaException {
        String[] taskArray = input.split(" ", 2);
        String action = taskArray[0];

        switch(action) {
            case "bye": 
            return new ExitCommand();

            case "list":
            return new ListCommand();

            case "delete":
            return new DeleteCommand(taskArray[1]);

            case "mark":
            return new MarkCommand(taskArray[1]);

            case "unmark":
            return new UnmarkCommand(taskArray[1]);

            case "todo":

            case "event":

            case "deadline":
            return new AddTaskCommand(input);

            case "find":
            return new FindCommand(taskArray[1]);

            case "help":
            return new HelpCommand();

            default:
            return new AnotherCommand();
        }
    }
}