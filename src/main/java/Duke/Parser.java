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

/**
* Parser deals with making sense of the user command by formatting user inputs
* such that Duke can handle them through the Command Class and Ui Class
*
* @author Linus Chui
*/
public class Parser {

    /**
     * Parses the user input and splits the input into a user command and user action
     * to be passed into a Command object to execute the respective commands.
     *
     * @param fullCommand the full user input as a String.
     * @return a Command object to execute the user input if it is valid.
     * @throws DukeException if the user input is invalid or insufficient.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] command = fullCommand.split("_______________");
        String userCommand = command[0];
        Command nextCommand = null;
        try {
            if (userCommand.equals("list")) {
                nextCommand = new ListCommand();
            } else if (userCommand.equals("mark")) {
                try {
                    String userAction = command[1];
                    nextCommand = new MarkCommand(userAction);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("I'm sorry, but you need to provide a valid index");
                }
            } else if (userCommand.equals("unmark")) {
                try {
                    String userAction = command[1];
                    nextCommand = new UnmarkCommand(userAction);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("I'm sorry, but you need to provide a valid index");
                }
            } else if (userCommand.equals("todo")) {
                try {
                    String userAction = command[1];
                    nextCommand = new TodoCommand(userAction);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("I'm sorry, but you need to provide a todo");
                }
            } else if (userCommand.equals("event")) {
                try {
                    String userAction = command[1];
                    nextCommand = new EventCommand(userAction);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("I'm sorry, but you need to provide a event");
                }
            } else if (userCommand.equals("deadline")) {
                try {
                    String userAction = command[1];
                    nextCommand = new DeadlineCommand(userAction);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("I'm sorry, but you need to provide a deadline");
                }
            } else if (userCommand.equals("delete")) {
                try {
                    String userAction = command[1];
                    nextCommand = new DeleteCommand(userAction);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("I'm sorry, but you need to provide a valid index");
                }
            } else if (userCommand.equals("find")) {
                try {
                    String userAction = command[1];
                    nextCommand = new FindCommand(userAction);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("I'm sorry, but you need to provide a keyword to search for");
                }
            } else if (userCommand.equals("bye")) {
                nextCommand = new ExitCommand();
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (NullPointerException e1){
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        } catch (DukeException e2) {
            DukeUi.sendMessage(e2.getMessage());
        }
        return nextCommand;
    }
}
