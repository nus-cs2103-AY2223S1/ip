package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Task;
import java.util.ArrayList;

/*
Find tasks that contain the given keyword.
 */
public class FindCommand extends Command {

    String taskName;

    public FindCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Executes the find command.
     * @param taskList List that contains existing tasks.
     * @param storage Storage that stores tasks into a txt file.
     * @param ui Ui that generates response messages.
     * @return The response of Dukie.
     * @throws DukeException Exception that is specific to duke.
     */
    public String exec(TaskList taskList, Storage storage , Ui ui) throws DukeException {
        ArrayList<Task> foundTasks = taskList.find(this.taskName);
        assert(ui != null);
        return ui.showFoundTasks(foundTasks, this.taskName);
    };

}