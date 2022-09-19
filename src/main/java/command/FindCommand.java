package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
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

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.list(tasks.search(keyword), true);
    }

    @Override
    public Task getTask() {
        return Task.empty();
    }
}
