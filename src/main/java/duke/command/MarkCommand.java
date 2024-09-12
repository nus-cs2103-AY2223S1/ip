package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;



/**
 * Encapsulates a command to mark a Task.
 */
public class MarkCommand extends Command{
    int index;


    /**
     * A constructor to create a MarkCommand class
     *
     * @param index the index of the class to be marked
     */
    public MarkCommand(int index) {
        this.index = index;
    }


    /**
     * A function that executes the effect of marking a task
     *  @param taskList stores the tasks of the program
     * @param storage reads and writes from the text file which stores the tasks in memory
     * @param ui interfaces with the user using the commandline
     * @return
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        String returnString;
        taskList.markDone(index);
        returnString = ui.showMark(taskList.getTask(index));
        storage.writeFile(taskList.getTaskList(),"duke.txt");
        return returnString;
    }
}
