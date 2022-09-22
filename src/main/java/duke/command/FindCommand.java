package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        assert keyword != null : "keyword cannot be null";
        this.keyword = keyword;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String foundTasks = tasks.findTasks(this.keyword);
        return ui.showFound(foundTasks);
    }

}
