package pikachu;

import pikachu.command.AddCommand;
import pikachu.command.Command;
import pikachu.command.DeleteCommand;
import pikachu.command.ExitCommand;
import pikachu.command.FindCommand;
import pikachu.command.ListCommand;
import pikachu.command.MarkCommand;
import pikachu.command.UnmarkCommand;
import pikachu.command.WrongCommand;

/**
 * Represents a parser to find the command for the input. A <code>Parser</code> object corresponds to
 * a parser to find input the right command
 */
public class Parser {

    /**
     * Returns the command corresponds to the user input
     * If the command is unclear, PikaChuException is threw
     *
     * @param fullCommand the full input from user.
     * @return command related to user input.
     * @throws IllegalArgumentException If cannot find the corresponding command.
     */
    public static Command parse(String fullCommand) {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("mark ")) {
            return new MarkCommand(fullCommand);
        } else if (fullCommand.startsWith("unmark ")) {
            return new UnmarkCommand(fullCommand);
        } else if (fullCommand.startsWith("todo ")
                || (fullCommand.startsWith("deadline ") && fullCommand.contains(" /by "))
                || (fullCommand.startsWith("event ") && fullCommand.contains(" /at "))) {
            return new AddCommand(fullCommand);
        } else if (fullCommand.startsWith("delete ")) {
            return new DeleteCommand(fullCommand);
        } else if (fullCommand.startsWith("find ")) {
            return new FindCommand(fullCommand);
        }
        return new WrongCommand();
    }


}
