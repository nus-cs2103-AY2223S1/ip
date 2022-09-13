package Duke;
import Command.Command;
import Command.DeadlineCommand;
import Command.DeleteCommand;
import Command.EventCommand;
import Command.ExitCommand;
import Command.FindCommand;
import Command.ListCommand;
import Command.MarkCommand;
import Command.TodoCommand;
import Command.UnmarkCommand;
import Command.UpdateCommand;

/**
* Parser deals with making sense of the user command by formatting user inputs
* such that Duke can handle them through the Command Class and Ui Class
*
* @author Linus Chui
*/
public class Parser {

    public static boolean hasDescription(String[] command) {
        return command[0].equals("mark") || command[0].equals("unmark") ||
                command[0].equals("todo") || command[0].equals("deadline") ||
                command[0].equals("event") || command[0].equals("find") ||
                command[0].equals("delete") || command[0].equals("update");
    }

    /**
     * Parses the user input and splits the input into a user command and user action
     * to be passed into a Command object to execute the respective commands.
     *
     * @param command the full user input as a String.
     * @return a Command object to execute the user input if it is valid.
     * @throws DukeException if the user input is invalid or insufficient.
     */
    public static Command parse(String[] command) throws DukeException {
        String userAction = "";
        boolean isDescriptionNeeded = Parser.hasDescription(command);
        if (isDescriptionNeeded && (command.length == 1)) {
            throw new DukeException(DukeUi.INVALID_DESCRIPTION);
        } else if (isDescriptionNeeded) {
            userAction = command[1].trim();
        }
        String userCommand = command[0].trim();
        switch (userCommand) {
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(userAction);
            case "unmark":
                return new UnmarkCommand(userAction);
            case "todo":
                return new TodoCommand(userAction);
            case "deadline":
                return new DeadlineCommand(userAction);
            case "event":
                return new EventCommand(userAction);
            case "delete":
                return new DeleteCommand(userAction);
            case "find":
                return new FindCommand(userAction);
            case "update":
                return new UpdateCommand(userAction);
            case "bye" :
                return new ExitCommand();
            default:
                throw new DukeException(userCommand + DukeUi.INVALID_COMMAND);
        }
    }
}
