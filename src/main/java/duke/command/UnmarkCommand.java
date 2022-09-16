package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;


/**
 * Encapsulates a command to unamrk a Task.
 */
public class UnmarkCommand extends Command {
    int index;

    /**
     * A constructor to create a UnmarkCommand class
     *
     * @param index the index of the class to be unmarked
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }


    /**
     * A function that executes the effect of unmarking a task
     *  @param taskList stores the tasks of the program
     * @param storage reads and writes from the text file which stores the tasks in memory
     * @param ui interfaces with the user using the commandline
     * @return
     */

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        String returnString;
        taskList.markNotDone(index);
        returnString = ui.showUnmark(taskList.getTask(index));
        storage.writeFile(taskList.getTaskList(),"duke.txt");
        return returnString;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

