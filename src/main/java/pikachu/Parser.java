package pikachu;

import pikachu.command.AddCommand;
import pikachu.command.Command;
import pikachu.command.DeleteCommand;
import pikachu.command.ExitCommand;
import pikachu.command.ListCommand;
import pikachu.command.MarkCommand;
import pikachu.command.UnmarkCommand;

public class Parser {

    public static Command parse(String fullCommand) throws PikachuException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("mark ")) {
            return new MarkCommand(fullCommand);
        } else if (fullCommand.startsWith("unmark ")) {
            return new UnmarkCommand(fullCommand);
        } else if (fullCommand.startsWith("todo ") || 
        (fullCommand.startsWith("deadline ") && fullCommand.contains(" /by ")) || 
        (fullCommand.startsWith("event ") && fullCommand.contains(" /at "))) {
            return new AddCommand(fullCommand);
        } else if (fullCommand.startsWith("delete ")) {
            return new DeleteCommand(fullCommand);
        }
        throw new PikachuException("Pi?");
    }


}
