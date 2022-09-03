package justin.command;

import justin.MainWindow;
import justin.Storage;
import justin.TaskList;
import justin.Ui;

public class FindCommand extends Command {
    private String description;

    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage, MainWindow mw) {}

    @Override
    public String getMessage(TaskList list, Ui ui) {
        return ui.findMessage(list, description);
    }
}
