package DukeUI.Command;

import DukeUI.TaskList;
import DukeUI.Ui;
import DukeUI.FileStorage.Storage;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showResponse(tasks.toString());
    }
}
