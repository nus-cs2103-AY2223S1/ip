package cinnamon.command;

import cinnamon.Exception.DukeException;
import cinnamon.Handler.Ui;
import cinnamon.Storage.Storage;
import cinnamon.Tasks.Task;
import cinnamon.Tasks.TaskList;

import java.util.ArrayList;

public class FindTask extends Command {

    String input;

    public FindTask(String input) {
        this.input = input;
    }

    /**
     * Lists task with the same name
     *
     * @param taskList containing all tasks added
     * @param ui gives messages
     * @param storage stores added tasks into a txt file
     * @return respone of cinnamon
     * @throws DukeException specific exceptions
     */

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] parts = input.split(" ", 2);
        ArrayList<Task> matched = new ArrayList<>();
        for (Task t : taskList.listTasks()) {
            String str = t.toString();
            if (str.contains(parts[1])) {
                matched.add(t);
                assert (ui != null);
                break;
            }
        }
        return ui.printMatchedTasks(matched);
    }
}
