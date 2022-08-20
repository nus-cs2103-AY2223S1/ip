package duke;

import duke.command.DukeCommandType;
import duke.command.Command;

public class Parser {

    public static Command getCommand(String line) {
        String parsedCommand = parseCommand(line);
        String command = parsedCommand.split(" ")[0];
        String args = line.replaceFirst(command, "").trim();
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
        default:
            return DukeCommandType.UNKNOWN;
        }
    }

}
