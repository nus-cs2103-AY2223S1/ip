package cinnamon.command;

import cinnamon.Exception.DukeException;
import cinnamon.Handler.Ui;
import cinnamon.Storage.Storage;
import cinnamon.Tasks.Task;
import cinnamon.Tasks.TaskList;

public class PrintTag extends Command {
    String input;

    public PrintTag(String input) {
        this.input = input;
    }

    /**
     * prints tag for a task
     *
     * @param taskList containing all tasks added
     * @param ui gives messages
     * @param storage stores added tasks into a txt file
     * @return response of cinnamon
     * @throws DukeException specific exceptions
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int index = Integer.parseInt(input.substring(10)) - 1;
        Task task = taskList.get(index);
        if (task.isTagged()) {
            String str = "Your task is tagged as " + task.getTag() + "\n";
            return str + task.toString();
        } else {
            return "The task is not tagged";
        }
    }
}
