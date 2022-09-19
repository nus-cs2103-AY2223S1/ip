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

    /**
     * Executes DeleteCommand by deleting chosen task from task list.
     *
     * @param taskList Task list containing the task to be deleted.
     * @param ui Ui to retrieve return string from.
     * @param s Storage containing the list of tasks or where tasks should be saved.
     * @return Delete string to be displayed by program.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage s) {
        String descript = taskList.retrieveTask(index).toString();
        taskList.deleteTask(index);
        return ui.printDeleteStatement(descript, taskList.getSize());
    }

}
