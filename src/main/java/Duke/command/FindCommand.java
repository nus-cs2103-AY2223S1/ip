package Duke.command;

import Duke.command.Command;
import Duke.tasklist.TaskList;
import Duke.utility.Storage;
import Duke.utility.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        super("find");
        assert keyword != null : "keyword for find command cannot be null";
        this.keyword = keyword;
    }


    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String listOfTasks = tasks.find(keyword);
        return ui.find(listOfTasks);
    }
}
