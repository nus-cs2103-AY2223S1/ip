package cinnamon.command;

import cinnamon.Exception.DukeException;
import cinnamon.Handler.Ui;
import cinnamon.Storage.Storage;
import cinnamon.Tasks.TaskList;

public class List extends Command {

    public List(){}

    /**
     * Lists all tasks added in task list
     *
     * @param taskList containing all tasks added
     * @param ui gives messages
     * @param storage stores added tasks into a txt file
     * @return respone of cinnamon
     * @throws DukeException specific exceptions
     */

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assert (ui != null);
        return ui.listTask(taskList);
    }


}
