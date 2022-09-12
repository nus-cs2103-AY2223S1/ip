package duke.command;

import duke.command.Command;
import duke.tasklist.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        super("find");
        this.keyword = keyword;
    }


    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String listOfTasks = tasks.find(keyword);
        return ui.find(listOfTasks);
    }
}
