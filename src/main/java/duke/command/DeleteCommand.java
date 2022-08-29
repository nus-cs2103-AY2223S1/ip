package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;


/**
 * Encapsulates a command to delete a Task.
 */
public class DeleteCommand extends Command {
    int index;

    /**
     * A constructor to create a DeleteCommand class
     *
     * @param index the index of the class to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * A function that executes the effect of deleting a task
     *  @param taskList stores the tasks of the program
     * @param storage reads and writes from the text file which stores the tasks in memory
     * @param ui interfaces with the user using the commandline
     * @return
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        String returnString;
        returnString = ui.showDelete(taskList.getTask(index), taskList.size()-1);
        taskList.delete(this.index);
        storage.writeFile(taskList.getTaskList(),"duke.txt");
        return returnString;
    }
}
