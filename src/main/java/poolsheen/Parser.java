package poolsheen;

import poolsheen.command.*;

import java.util.ArrayList;

/**
 * Class that deals with parsing strings into commands.
 * @author Ong Wee, Marcus (Tut Grp 03)
 * @version CS2103 AY22/23 Sem 1
 */
public class Parser {
    /**
     * Returns a Command by checking the input of a string
     * @param fullCommand The full string command given by the user.
     * @return A Command that will be executed later.
     */
    public static Command parse(String fullCommand) {
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
        default:
            throw new UnknownCommandException(String.join(" ", arl));
        }
    }
}
