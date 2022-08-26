package duke.commands;

import duke.TaskList;
import duke.Storage;
import duke.DukeException;

public class ByeCommand implements Command {

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        System.out.println("Bye. Hope to see you again soon!");
        storage.writeAll(tasks);
    }
}
