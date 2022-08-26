package duke.command;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.util.DukeIo;
import duke.util.Storage;
import duke.util.TaskList;

public class ByeCommand implements Command {
    private static final String OUTRO = "Bye. Hope to see you again soon!";

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, DukeIo io, Storage storage) throws DukeException, IOException {
        io.printTask(OUTRO, 3);
        storage.saveTasks(tasks);
        System.exit(0);
    }
    
}
