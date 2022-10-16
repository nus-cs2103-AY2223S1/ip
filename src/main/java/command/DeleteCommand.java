package command;

import duke.TaskList;
import task.Task;

/**
 *  A class which encapsulates the delete command of Duke.
 *  @author  Chen Guanzhou
 *  @version v2
 */
public class DeleteCommand extends Command {
    TaskList currList;
    int taskNumber;

    public DeleteCommand(int index, TaskList currList) {
        this.currList = currList;
        this.taskNumber = index;
    }

    /**
     * Executes the delete command and shows the deleted task.
     * @return Duke's response to be passed into the Dialog Box.
     */
    @Override
    public String execute() {
        Task toBeDeleted = currList.getTaskAt(this.taskNumber);
        String result = "Alrighty, this task's gone:\n";
        result += toBeDeleted;
        currList.removeTask(toBeDeleted);
        return result;
    }
}
