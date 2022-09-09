package bob.commands;

import bob.Storage;
import bob.Task;
import bob.TaskList;
import bob.Ui;

/**
 * RemoveCommand class to handle "remove" keyword
 */
public class RemoveCommand extends Command {

    private int index;

    /**
     * Constructor for RemoveCommand
     *
     * @param index index of task in the list to be removed
     */
    public RemoveCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage, Ui ui) {
        Task removedTask = taskList.getTask(this.index);
        taskList.removeTask(this.index);
        storage.save(taskList);
        return ui.displayRemoved(taskList, removedTask);
    }
}
