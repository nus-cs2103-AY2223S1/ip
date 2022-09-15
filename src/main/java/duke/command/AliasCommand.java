package duke.command;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidAliasFormatException;
import duke.inputoutput.DukeIo;
import duke.util.ParsedData;
import duke.util.Storage;
import duke.util.StringParser;
import duke.util.TaskList;

/**
 * Command to add an alias for a current command
 */
public class AliasCommand extends DataCommand {

    private static final String ADD_TASK = "I have added %s -> %s!";

    /**
     * Create a instance of the deadline command.
     *
     * @param d ParsedData from the command input
     */
    public AliasCommand(ParsedData d) {
        super(d);
    }

    /**
     * {@inheritDoc} Adds a deadline task to the tasklist and prints it.
     *
     * @throws DukeException Thrown when invalid/missing data
     * @throws IOException   Thrown when saving of data failed
     */
    @Override
    public void execute(TaskList tasks, DukeIo io, Storage storage, CommandSelector cs)
            throws DukeException {

        String[] commandMapping = data.description.split("\\s*->\\s*", 2);
        if (commandMapping.length != 2) {
            throw new InvalidAliasFormatException();
        }

        String alias = commandMapping[0];
        String target = commandMapping[1];

        if (StringParser.containWhitespace(alias)) {
            throw new InvalidAliasFormatException();
        }

        cs.addAlias(alias, target);
        io.printTask(String.format(ADD_TASK, alias, cs.getCommand(target)));
    }

}
