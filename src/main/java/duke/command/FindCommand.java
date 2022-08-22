package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        TaskList matchingTaskList = taskList.findMatchingTasks(keyword);
        ui.printList(matchingTaskList.toString());
    }
}
