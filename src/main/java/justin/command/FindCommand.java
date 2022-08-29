package justin.command;

import justin.Storage;
import justin.TaskList;
import justin.Ui;

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
