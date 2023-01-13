package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    public String execute(TaskList tasks, Storage storage) {
        return Ui.getListString(tasks);
    }

}
