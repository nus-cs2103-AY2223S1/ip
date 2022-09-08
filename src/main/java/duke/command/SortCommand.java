package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.task.Task;
import duke.utils.Storage;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

/**
 * Handles the "sort" command.
 * @author Jason
 */
public class SortCommand extends  Command {
    @Override
    public String run(TaskList taskList, Storage storage) throws DukeException, IOException {
        taskList.getList().sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
        storage.saveData(taskList.getList());
        return "Your Tasks has been sorted!";
    }
}
