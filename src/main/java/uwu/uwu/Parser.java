package uwu.uwu;

import uwu.command.AddDeadlineCommand;
import uwu.command.AddEventCommand;
import uwu.command.AddToDosCommand;
import uwu.command.Command;
import uwu.command.DeleteCommand;
import uwu.command.ExitCommand;
import uwu.command.FindCommand;
import uwu.command.HelpCommand;
import uwu.command.ListCommand;
import uwu.command.MarkCommand;
import uwu.command.UnmarkCommand;
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
        String lowerCaseUserCmd = userCommand.toLowerCase();

        if (lowerCaseUserCmd.equals("bye")) {
            return new ExitCommand();
        } else if (lowerCaseUserCmd.trim().equals("list")) {
            return new ListCommand();
        } else if (lowerCaseUserCmd.startsWith("mark")) {
            return new MarkCommand(userCommand);
        } else if (lowerCaseUserCmd.startsWith("unmark")) {
            return new UnmarkCommand(userCommand);
        } else if (lowerCaseUserCmd.startsWith("todo")) {
            return new AddToDosCommand(userCommand);
        } else if (lowerCaseUserCmd.startsWith("deadline")) {
            return new AddDeadlineCommand(userCommand);
        } else if (lowerCaseUserCmd.startsWith("event")) {
            return new AddEventCommand(userCommand);
        } else if (lowerCaseUserCmd.startsWith("delete")) {
            return new DeleteCommand(userCommand);
        } else if (lowerCaseUserCmd.startsWith("find")) {
            return new FindCommand(userCommand);
        } else if (lowerCaseUserCmd.startsWith("help")) {
            return new HelpCommand();
        } else {
            throw new UnknownCommandException("sorry >< \ni don't know what that means TT");
        }
    }
}
