/**
 * The Parser deals with making sense of the user command
 *
 */
public class Parser {

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
