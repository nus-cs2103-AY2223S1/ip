package duke.command;

import duke.inputoutput.DukeIo;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Command to list out all the current tasks.
 */
public class ListCommand implements Command {

    /**
     * {@inheritDoc} List command does not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints out all the current tasks added.
     */
    @Override
    public void execute(TaskList tasks, DukeIo io, Storage storage, CommandSelector cs) {
        io.printList(tasks.getTasks());
    }

}
