package poolsheen;

import java.util.ArrayList;

import poolsheen.command.ByeCommand;
import poolsheen.command.Command;
import poolsheen.command.DeadlineCommand;
import poolsheen.command.DeleteCommand;
import poolsheen.command.EventCommand;
import poolsheen.command.FindCommand;
import poolsheen.command.ListCommand;
import poolsheen.command.MarkCommand;
import poolsheen.command.ToDoCommand;
import poolsheen.command.UnmarkCommand;
import poolsheen.command.UpdateCommand;

/**
 * Represents a class that can parse strings into commands.
 */
public class Parser {
    /**
     * Returns a Command by checking the input of a string.
     *
     * @param fullCommand The full string command given by the user.
     * @return A Command that will be executed later.
     */
    public static Command parse(String fullCommand) {
        assert fullCommand != null : "The command that is being parsed should not be null";
        String[] arr = fullCommand.split(" ");
        ArrayList<String> arl = new ArrayList<>();
        for (String s : arr) {
            arl.add(s);
        }
        String firstWord = arl.get(0);
        arl.remove(0);
        switch (firstWord.toUpperCase()) {
        case "BYE":
            return new ByeCommand(arl);
        case "LIST":
            return new ListCommand(arl);
        case "MARK":
            return new MarkCommand(arl);
        case "UNMARK":
            return new UnmarkCommand(arl);
        case "DELETE":
            return new DeleteCommand(arl);
        case "TODO":
            return new ToDoCommand(arl);
        case "DEADLINE":
            return new DeadlineCommand(arl);
        case "EVENT":
            return new EventCommand(arl);
        case "FIND":
            return new FindCommand(arl);
        case "UPDATE":
            return new UpdateCommand(arl);
        default:
            throw new PoolsheenException(firstWord, "unknown command", "Input a valid command.");
        }
    }
}
