package cinnamon.command;

import cinnamon.Exception.DukeException;
import cinnamon.Handler.Ui;
import cinnamon.Storage.Storage;
import cinnamon.Tasks.Task;
import cinnamon.Tasks.TaskList;

public class Mark extends Command {

    String input;

    public Mark(String input) {
        this.input = input;
    }

    /**
     * Marks a task as done
     *
     * @param taskList containing all tasks added
     * @param ui gives messages
     * @param storage stores added tasks into a txt file
     * @return response of cinnamon
     * @throws DukeException specific exceptions
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (input.length() <= 5) {
            throw new DukeException("☹ OOPS!!! Please enter the index of the task that you want to mark.");
        }
        int index = Integer.parseInt(input.substring(5)) - 1;
        Task task = taskList.get(index);
        task.markAsDone();
        assert (ui != null);
        return ui.markDoneMes() + task.toString();
    }

}
