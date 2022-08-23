package jean.command;

import jean.storage.Storage;
import jean.task.TaskList;
import jean.ui.Ui;

import java.io.IOException;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.findTask(this.keyword, ui);
    }
}
