package duke;

import command.*;

/**
 * Parse user inputs in Duke to return command object
 */
public class Parser {

    public static Command parse(String fullCommand) {

        fullCommand += " ";
        String inst = fullCommand.substring(0, fullCommand.indexOf(' ')).trim();

        switch (inst) {
        case "bye":
        // for now all of the strings will be fullCommand until further updates
            return new ByeCommand();            

        case "list":
            return new ListCommand(fullCommand);            

        case "unmark":
            return new UnmarkCommand(fullCommand);            

        case "mark":
            return new MarkCommand(fullCommand);            

        case "delete":
            return new DeleteCommand(fullCommand.trim());            

        case "todo":
            return new ToDoCommand(fullCommand);            

        case "deadline":
            return new DeadlineCommand(fullCommand.substring(8).trim());
            
        case "event":
            return new EventCommand(fullCommand.substring(5).trim());

        default:
            return new NoCommand();
        }
    }


}
