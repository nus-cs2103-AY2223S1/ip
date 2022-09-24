package cinnamon.command;

import cinnamon.Exception.DukeException;
import cinnamon.Handler.Ui;
import cinnamon.Storage.Storage;
import cinnamon.Tasks.Task;
import cinnamon.Tasks.TaskList;

public class DeleteTask extends Command{

    String input;

    public DeleteTask(String input) {
        this.input = input;
    }

    /**
     * Deletes a task
     *
     * @param taskList containing all tasks added
     * @param ui gives messages
     * @param storage stores added tasks into a txt file
     * @return response of cinnamon
     * @throws DukeException specific exceptions
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (input.length() <= 7) {
            throw new DukeException("☹ OOPS!!! Please enter the index of the task that you want to delete.");
        }
        int index = Integer.parseInt(input.substring(7)) - 1;
        Task task = taskList.get(index);
        taskList.remove(index);
        assert (ui != null);
        return ui.deleteTask() + task.toString();
    }
}
