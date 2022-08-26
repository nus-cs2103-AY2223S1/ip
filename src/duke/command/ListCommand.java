package duke.command;

import duke.util.DukeIo;
import duke.util.Storage;
import duke.util.TaskList;

public class ListCommand implements Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, DukeIo io, Storage storage) {
        io.printList(tasks.getTasks());        
    }
    
}
