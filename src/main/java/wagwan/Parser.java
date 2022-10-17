package wagwan;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import command.UpdateCommand;

/**
* Parser deals with making sense of the user command by formatting user inputs
* such that Duke can handle them through the Command Class and Ui Class
*
* @author Linus Chui
*/
public class Parser {

    /**
     * Checks if a given command requires a description field.
     *
     * @param command the command of user input.
     * @return True if the command of the user input requires a description field.
     */
    public static boolean hasDescription(String[] command) {
        return command[0].equals("mark") || command[0].equals("unmark")
                || command[0].equals("todo") || command[0].equals("deadline")
                || command[0].equals("event") || command[0].equals("find")
                || command[0].equals("delete") || command[0].equals("update");
    }

    /**
     * Parses the user input and splits the input into a user command and user action
     * to be passed into a Command object to execute the respective commands.
     *
     * @param command the full user input as a String.
     * @return a Command object to execute the user input if it is valid.
     * @throws WagwanException if the user input is invalid or insufficient.
     */
    public static Command parse(String[] command) throws WagwanException {
        String userAction = "";
        boolean isDescriptionNeeded = Parser.hasDescription(command);
        if (isDescriptionNeeded && (command.length == 1)) {
            throw new WagwanException(WagwanUi.INVALID_DESCRIPTION);
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
        case "bye":
            return new ExitCommand();
        default:
            throw new WagwanException(userCommand + WagwanUi.INVALID_COMMAND);
        }
    }
}
