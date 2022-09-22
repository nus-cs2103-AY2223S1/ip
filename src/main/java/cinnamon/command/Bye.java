package cinnamon.command;

import cinnamon.Exception.DukeException;
import cinnamon.Handler.Ui;
import cinnamon.Storage.Storage;
import cinnamon.Tasks.TaskList;

public class Bye extends Command {


    /**
     * Executes a bye command
     *
     * @param taskList containing all tasks added
     * @param ui gives messages
     * @param storage stores added tasks into a txt file
     * @return response of cinnamon
     * @throws DukeException specific exceptions
     */
    public String execute (TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.writeTasks(taskList);
        assert (ui != null);
        return ui.bye();
    }
}
