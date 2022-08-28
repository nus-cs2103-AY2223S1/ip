package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String keyword;
    
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFindMessage(this.keyword, tasks.find(this.keyword));
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
