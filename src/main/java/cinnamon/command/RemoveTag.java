package cinnamon.command;

import cinnamon.Exception.DukeException;
import cinnamon.Handler.Ui;
import cinnamon.Storage.Storage;
import cinnamon.Tasks.Task;
import cinnamon.Tasks.TaskList;

public class RemoveTag extends Command{

    String input;

    public RemoveTag(String input) {
        this.input = input;
    }

    /**
     * Removes a tag for task
     *
     * @param taskList containing all tasks added
     * @param ui gives messages
     * @param storage stores added tasks into a txt file
     * @return response of cinnamon
     * @throws DukeException specific exceptions
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] parts = input.split(" ", 3);
        int index = Integer.parseInt(parts[2]) - 1;
        Task task = taskList.get(index);
        task.setTag("");
        task.unTag();
        assert (ui != null);
        return ui.removeTag();
    }
}
