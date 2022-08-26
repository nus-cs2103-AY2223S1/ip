package duke.command;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.util.DukeIo;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Interface for command containing a method to be excecuted.
 */
public interface Command {
    /**
     * Returns whether the program should exit after execution.
     * 
     * @return
     */
    boolean isExit();

    /**
     * Runs a given command.
     * 
     * @param tasks   TaskList object used by the main loop
     * @param io      DukeIo object used to handle IO
     * @param storage Storage used to interact with file system
     * @throws DukeException Throws in case of error
     * @throws IOException   Throws in case of error when saving
     */
    void execute(TaskList tasks, DukeIo io, Storage storage) throws DukeException, IOException;
}
