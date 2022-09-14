package duke.command;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.inputoutput.DukeIo;
import duke.util.ParsedData;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Command that deletes an added alias
 */
public class DeleteAliasCommand extends DataCommand {

    private static final String DELETE_TASK = "I have deleted the alias, %s!";

    /**
     * Create a instance of the deadline command.
     * 
     * @param d ParsedData from the command input
     */
    public DeleteAliasCommand(ParsedData d) {
        super(d);
    }

    /**
     * {@inheritDoc} Adds a deadline task to the tasklist and prints it.
     * 
     * @throws DukeException Thrown when invalid/missing data
     * @throws IOException Thrown when saving of data failed
     */
    @Override
    public void execute(TaskList tasks, DukeIo io, Storage storage, CommandSelector cs)
            throws DukeException {

        String alias = data.description.trim();

        cs.deleteAlias(alias);
        io.printTask(String.format(DELETE_TASK, alias));
    }

}
