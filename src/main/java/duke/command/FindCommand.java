package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList resultsList = tasks.filter(task ->
                task.getDescription().contains(searchTerm));
        ui.showOutput("Here are the tasks in your list: \n" + resultsList);
    }
}
