package duke.parser;

import duke.command.Command;

/**
 * Class which deals with making sense of the user command.
 *
 * @author Shawn Chew
 * @version CS2103T AY 22/23 Sem 1
 */
public class Parser {
    /**
     * Parses the command given to the respective Command object.
     *
     * @param comm The command given.
     * @return A Command object depending on the command given.
     */
    public static Command parse(String comm) {
        return Command.of(comm);
    }
}
