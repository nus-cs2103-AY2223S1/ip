package cinnamon.command;

import cinnamon.Exception.DukeException;
import cinnamon.Handler.Ui;
import cinnamon.Storage.Storage;
import cinnamon.Tasks.Task;
import cinnamon.Tasks.TaskList;

public class Tag extends Command{

    String input;

    public Tag(String input) {
        this.input = input;
    }

    /**
     * Tags a task
     *
     * @param taskList containing all tasks added
     * @param ui gives messages
     * @param storage stores added tasks into a txt file
     * @return response of cinnamon
     * @throws DukeException specific exceptions
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int index = Integer.parseInt(input.substring(4, 5)) - 1;
        Task task = taskList.get(index);
        String str = "Your task " + task.toString() + " is tagged as: ";
        task.tagged();
        String[] parts = input.split(" ", 3);
        task.setTag(parts[2]);
        return str + task.getTag();
    }
}
