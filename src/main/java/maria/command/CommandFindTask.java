package maria.command;

import maria.Storage;
import maria.Ui;
import maria.task.Task;
import maria.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class CommandFindTask extends Command {

    private String searchString;

    public CommandFindTask(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        List<Task> filteredList = new ArrayList<>();

        for (Task t : taskList) {
            if (t.nameContainsString(this.searchString)) {
                filteredList.add(t);
            }
        }

        if (filteredList.size() > 0) {
            ui.showText("Here are the matching results:");
            for (Task t : filteredList) {
                ui.showText(t.toString());
            }
        } else {
            ui.showText("There are no matching results.");
        }

    }

}
