package pikachu;

import pikachu.command.*;

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
        } else if (fullCommand.startsWith("todo ")
                || (fullCommand.startsWith("deadline ") && fullCommand.contains(" /by "))
                || (fullCommand.startsWith("event ") && fullCommand.contains(" /at "))) {
            return new AddCommand(fullCommand);
        } else if (fullCommand.startsWith("delete ")) {
            return new DeleteCommand(fullCommand);
        } else if (fullCommand.startsWith("find ")) {
            return new FindCommand(fullCommand);
        }
        throw new PikachuException("Pi?");
    }


}
