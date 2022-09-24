package cinnamon.command;

import cinnamon.Exception.DukeException;
import cinnamon.Handler.Ui;
import cinnamon.Storage.Storage;
import cinnamon.Tasks.Task;
import cinnamon.Tasks.TaskList;

import java.util.ArrayList;

public class FindTag extends Command{

    String input;

    public FindTag(String input) {
        this.input = input;
    }

    /**
     * Lists all tasks with the same tag
     *
     * @param taskList containing all tasks added
     * @param ui gives messages
     * @param storage stores added tasks into a txt file
     * @return respone of cinnamon
     * @throws DukeException specific exceptions
     */

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String s = "";
        ArrayList<Task> matched = new ArrayList<>();
        for (Task t : taskList.listTasks()) {
            if (t.getTag().equals(input)) {
                matched.add(t);
            }
        }
        if (matched.isEmpty()) {
            return "No matching task of given tag";
        } else {
            assert (ui != null);
            return ui.printMatchedTasks(matched);
        }
    }
}
