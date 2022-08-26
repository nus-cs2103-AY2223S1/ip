package commands;

import duke.Ui;
import tasks.*;

/**
 * DeleteCommand deletes the chosen task from the task list.
 */
public class DeleteCommand extends Command {
    int index;

    /**
     * Constructor for DeleteCommand.
     *
     * @param index Index of task in task list to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    /**
     * Executes DeleteCommand by deleting chosen task from task list.
     *
     * @param taskList Task list containing the task to be deleted.
     */
    public void run(TaskList taskList) {
        String descript = taskList.retrieveTask(index).toString();
        taskList.deleteTask(index);
        Ui.deleteStatement(descript, taskList.getSize());
    }

}
