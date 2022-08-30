package command;

import tasklist.TaskList;
import utility.Storage;
import utility.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        super("find");
        this.keyword = keyword;
    }


    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String listOfTasks = tasks.find(keyword);
        ui.find(listOfTasks);
    }
}
