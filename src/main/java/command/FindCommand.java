package command;

import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

public class FindCommand extends Command{
    
    private String keyword;
    
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.list(tasks.search(keyword), true);
    }

    @Override
    public Task getTask() {
        return Task.empty();
    }
}
