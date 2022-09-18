package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Task;
import java.util.ArrayList;

public class FindCommand extends Command {

    String taskName;

    public FindCommand(String taskName) {
        this.taskName = taskName;
    }

    public String exec(TaskList taskList, Storage storage , Ui ui) throws DukeException {
        ArrayList<Task> foundTasks = taskList.find(this.taskName);
        assert(ui != null);
        return ui.showFoundTasks(foundTasks, this.taskName);
    };

}