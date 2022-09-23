package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.DukeException;

/*
Writes items to file and exits program.
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command.
     * @param taskList List that contains existing tasks.
     * @param storage Storage that stores tasks into a txt file.
     * @param ui Ui that generates response messages.
     * @return The response of Dukie.
     * @throws DukeException Exception that is specific to duke.
     */
    public String exec(TaskList taskList, Storage storage , Ui ui) throws DukeException {
        storage.writeItems(taskList.getTaskList());
        assert(ui != null);
        return ui.showByeMessage();
    };

}