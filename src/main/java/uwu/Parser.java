package uwu;

import uwu.command.AddCommand;
import uwu.command.Command;
import uwu.command.DeleteCommand;
import uwu.command.ExitCommand;
import uwu.command.ListCommand;
import uwu.command.MarkCommand;

import uwu.exception.EmptyInputException;
import uwu.exception.UnknownCommandException;
import uwu.exception.UwuException;

/**
 * Parses user input.
 */
public class Parser {
    /**
     * Parses the user input to determine which command to execute.
     *
     * @param userCommand The user input.
     * @return The command to be executed.
     * @throws UwuException If the command is unknown;
     *                      If the task description is empty.
     */
    public static Command parse(String userCommand) throws UwuException {
            if (userCommand.equals("bye")) {
                return new ExitCommand();
            } else if (userCommand.trim().equals("list")) {
                return new ListCommand();
            } else if (userCommand.startsWith("mark") || userCommand.startsWith("unmark")) {
                return new MarkCommand(userCommand);
            } else if (userCommand.startsWith("todo")
                    || userCommand.startsWith("deadline")
                    || userCommand.startsWith("event")) {
                if (userCommand.trim().endsWith("todo")
                        || userCommand.trim().endsWith("deadline")
                        || userCommand.trim().endsWith("event")) {
                    throw new EmptyInputException("\tyour task description is empty TT\n\t"
                            + "feed me a task description to get started! <:");
                }
                return new AddCommand(userCommand);
            } else if (userCommand.startsWith("delete")) {
                return new DeleteCommand(userCommand);
            } else {
                throw new UnknownCommandException("\tsorry >< \n\ti don't know what that means TT");
            }
    }
}
