package duke.command;

import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class FindCommand extends Command {

    private String description;
    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.find(this.description, taskList);
    }
}
