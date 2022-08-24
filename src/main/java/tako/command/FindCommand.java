package tako.command;

import tako.Storage;
import tako.TaskList;
import tako.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFind(tasks.find(keyword));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
