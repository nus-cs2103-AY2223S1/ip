package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.DukeException;

/*
Shows the user all tasks in their task list.
 */
public class ListCommand extends Command {

    public ListCommand() {}

    /**
     * Executes the list command.
     * @param taskList List that contains existing tasks.
     * @param storage Storage that stores tasks into a txt file.
     * @param ui Ui that generates response messages.
     * @return The response of Dukie.
     * @throws DukeException Exception that is specific to duke.
     */
    public String exec(TaskList taskList, Storage storage , Ui ui) throws DukeException {
        assert(ui != null);
        return ui.showList(taskList);
    };

}
