package duke.command;

import java.io.IOException;

import duke.exceptions.UnknownCommandException;
import duke.inputoutput.DukeCliSettings;
import duke.inputoutput.DukeIo;
import duke.util.ParsedData;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Command class that exit the program. When bye is entered
 */
public class ByeCommand extends NoParamCommand {
    private static final String OUTRO = "Pff.. Not like I want to see you again";

    public ByeCommand(ParsedData data) {
        super(data);
    }

    /**
     * Returns true when asked if program should exit.
     *
     * @return boolean
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * {@inheritDoc} Prints goodbye message and exits program.
     *
     * @throws IOException raised if an error occured when saving
     * @throws UnknownCommandException when extra parameters is included
     */
    @Override
    public void execute(TaskList tasks, DukeIo io, Storage storage) throws IOException, UnknownCommandException {
        checkSingleArgumentGuard();
        io.printTask(OUTRO, DukeCliSettings.WRAP_INDENT);
        storage.saveTasks(tasks);
    }

}
