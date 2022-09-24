package cinnamon.command;

import cinnamon.Exception.DukeException;
import cinnamon.Handler.Ui;
import cinnamon.Storage.Storage;
import cinnamon.Tasks.Task;
import cinnamon.Tasks.TaskList;

public class Unmark extends Command{
    String input;

    public Unmark(String input) {
        this.input = input;
    }

    /**
     * Marks a task as not done
     *
     * @param taskList containing all tasks added
     * @param ui gives messages
     * @param storage stores added tasks into a txt file
     * @return response of cinnamon
     * @throws DukeException specific exceptions
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (input.length() <= 7) {
            throw new DukeException("☹ OOPS!!! Please enter the index of the task that you want to unmark.");
        }
        int index = Integer.parseInt(input.substring(7)) - 1;
        Task task = taskList.get(index);
        task.markAsNotDone();
        assert (ui != null);
        return ui.unmarkedMes() + task.toString();
    }

}
