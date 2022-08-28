package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private String description;

    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.findMessage(list, description);
    }
}
