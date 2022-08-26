package duke.command;

import duke.exception.UnknownCommandException;

import java.util.Set;

/**
 * Parser class to parse command and inputs entered by the user.
 */
public class Parser {

    /** Set to store all known commands. */
    private static final Set<String> commandList = Set.of("bye", "list", "mark", "unmark", "todo"
            , "deadline", "event", "delete", "find");

    /**
     * Returns a command based on the input string.
     *
     * @param input The string that was input by the user.
     * @return The command respective to the input.
     * @throws UnknownCommandException If the input command is invalid.
     */
    public static Command parse(String input) throws UnknownCommandException {
        String cmd = null, postCmd = null;
        String postSplit[];
        postSplit = input.split(" ");
        cmd = postSplit[0];
        if (!commandList.contains(cmd)) {
            throw new UnknownCommandException(cmd);
        }
        postCmd = cmd.length() != input.length() ? input.substring(cmd.length() + 1) : "";

        switch (cmd) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return new TodoCommand(postCmd);
        case "deadline":
            return new DeadlineCommand(postCmd);
        case "event":
            return new EventCommand(postCmd);
        case "mark":
        case "unmark":
            return new MarkUnmarkCommands(cmd, postCmd);
        case "delete":
            return new DeleteCommand(postCmd);
        case "find":
            return new FindCommand(postCmd);
        }

        return null;
    }
}
