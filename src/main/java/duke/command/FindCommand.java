package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String searchTerm;

    public FindCommand(String searchTerm) {
        super();
        this.searchTerm = searchTerm;
    }

    @Override
    public String read(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder result = new StringBuilder();
        int index = 1;
        for (int i = 0; i < taskList.getNumOfTasks(); i++) {
            if (taskList.getDescription(i).contains(searchTerm)) {
                result.append("\n").append(index).append(".").append(taskList.readTask(i));
                index++;
            }
        }
        if (result.toString().equals("")) {
            return ui.getNoMatchingTaskMessage() + searchTerm;
        } else {
            return ui.getMatchingTaskMessage() + result;
        }
    }
}
