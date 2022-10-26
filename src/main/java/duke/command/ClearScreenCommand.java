package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Encapsulates clearing the stage's scrollPane.
 *
 * @author Kartikeya
 */
public class ClearScreenCommand implements Command {
    @Override
    public String execute(TaskList itemList, Storage storage) throws DukeException {
        return "Screen cleared!";
    }
}
