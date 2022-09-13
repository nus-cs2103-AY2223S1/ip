package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.TaskList;
import duke.utils.Storage;

/**
 * Handles the "sort" command.
 * @author Jason
 */
public class SortCommand extends Command {
    /**
     * Sorts the task list.
     * @param taskList TaskList to sort.
     * @param storage Storage to save new sorted tasklist.
     * @return String message of running the "sort" command.
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws DukeException, IOException {
        //@@author Stimpson Cat-reused
        //Reused from https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date
        // with minor modifications
        taskList.getList().sort((task1, task2) -> task1.getDate().compareTo(task2.getDate()));
        storage.saveData(taskList.getList());
        return "Your Tasks has been sorted!";
    }
}
