package duke;

import duke.command.Command;
import duke.command.DukeCommandType;

/**
 * Encapsulates a Parser
 */
public class Parser {

    /**
     * Returns a command to be executed from a string of input
     *
     * @param line Input from the user
     * @return A Duke command
     * */
    public static Command getCommand(String line) {
        String parsedCommand = parseCommand(line);
        String command = parsedCommand.split(" ")[0];
        String args = parsedCommand.replaceFirst(command, "").trim();
        DukeCommandType commandType = getCommandType(command);
        return new Command(commandType, args);
    }

    private static String parseCommand(String args) {
        return args.replaceAll("( )+", " ");
    }

    private static DukeCommandType getCommandType(String command) {
        switch(command) {
        case "exit":
        case "quit":
            //Fallthrough
        case "bye":
            return DukeCommandType.EXIT;
        case "list":
            return DukeCommandType.LIST;
        case "mark":
            return DukeCommandType.MARK;
        case "unmark":
            return DukeCommandType.UNMARK;
        case "delete":
        case "remove":
            return DukeCommandType.DELETE;
        case "todo":
            return DukeCommandType.TODO;
        case "deadline":
            return DukeCommandType.DEADLINE;
        case "event":
            return DukeCommandType.EVENT;
        case "find":
            return DukeCommandType.FIND;
        case "update":
            return DukeCommandType.UPDATE;
        default:
            return DukeCommandType.UNKNOWN;
        }
    }

}
