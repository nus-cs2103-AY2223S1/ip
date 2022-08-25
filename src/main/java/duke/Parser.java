package duke;

import command.*;

/**
 * The class that deals with making sense of the user command in the Duke program.
 *
 * @author ShummyOwnzYou
 * @version 0.1
 */

public class Parser {

    /**
     * Parses the user input and returns a command corresponding to the user input.
     *
     * @param str The user input.
     * @return The command corresponding to the user input.
     */

    public static Command parse(String str) throws DukeException {
        try {
            if (str.equals("bye")) {
                return new ByeCommand();
            }
            if (str.equals("list")) {
                return new ListCommand();
            } else if (str.startsWith("mark ")) {
                return new MarkCommand(str);
            } else if (str.startsWith("unmark ")) {
                return new UnmarkCommand(str);
            } else if (str.startsWith("todo ")) {
                return new TodoCommand(str);
            } else if (str.startsWith("deadline ")) {
                return new DeadlineCommand(str);
            } else if (str.startsWith("event ")) {
                return new EventCommand(str);
            } else if (str.startsWith("delete ")) {
                return new DeleteCommand(str);
            } else if (str.equals("todo")) { // to avoid strings such as "todotodo for earlier block"
                return new ErrorCommand("☹ OOPS!!! The description of a todo cannot be empty.");
            } else {
                return new ErrorCommand("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid index input, please try again.");
        }
        return null;
    }

}
