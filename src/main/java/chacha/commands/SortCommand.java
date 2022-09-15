package chacha.commands;

import chacha.Storage;
import chacha.TaskList;
import chacha.Ui;
import chacha.tasks.Task;

import java.util.List;

/**
 * Command to list tasks from task list.
 */
public class SortCommand extends Command {

    /** 
     * Sorts all tasks in given task list in chronological order.
     * 
     * @param taskList Task list to sort tasks from.
     * @param ui Ui to handle printing message.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> newTasks = taskList.sort();
        TaskList newTaskList = new TaskList();
        for (Task task : newTasks) {
            newTaskList.add(task);
        }
        ui.printList(newTaskList);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
