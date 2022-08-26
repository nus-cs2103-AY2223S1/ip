package duke.command;

import java.io.IOException;

import duke.util.DukeIo;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Command class that exit the program. When bye is entered
 */
public class ByeCommand implements Command {
    private static final String OUTRO = "Bye. Hope to see you again soon!";

    /**
     * Returns true when asked if program should exit.
     * @return boolean
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * {@inheritDoc} Prints goodbye message and exits program.
     * @throws IOException raised if an error occured when saving
     */
    @Override
    public void execute(TaskList tasks, DukeIo io, Storage storage) throws IOException {
        io.printTask(OUTRO, 3);
        storage.saveTasks(tasks);
    }

}
