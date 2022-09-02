package commands;

import duke.Storage;
import duke.Ui;
import tasks.TaskList;

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
    public String execute(TaskList taskList, Ui ui, Storage s) {
        String descript = taskList.retrieveTask(index).toString();
        taskList.deleteTask(index);
        return ui.printDeleteStatement(descript, taskList.getSize());
    }

}
