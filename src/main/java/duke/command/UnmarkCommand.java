package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/*
Marks a task as undone.
 */
public class UnmarkCommand extends Command{

    String taskName;

    public UnmarkCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Executes the unmark command.
     * @param taskList List that contains existing tasks.
     * @param storage Storage that stores tasks into a txt file.
     * @param ui Ui that generates response messages.
     * @return The response of Dukie.
     * @throws DukeException Exception that is specific to duke.
     */
    public String exec(TaskList taskList, Storage storage , Ui ui) throws DukeException {
        assert(taskList != null);
        boolean isSuccess = taskList.unmarkDone(this.taskName);
        return ui.showUnmarkMessage(isSuccess, this.taskName);
    };

}
