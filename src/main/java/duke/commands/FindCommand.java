package duke.commands;

import duke.exceptions.DukeException;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

public class FindCommand implements Command {

    private String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.sayFind();
            for (int i = 0; i < taskList.getSize(); i++) {
                if (taskList.getTask(i).getDescription().contains(searchString)) {
                    ui.sayTaskWithIndex(i, taskList.getTask(i));
                }
            }
            ui.sayFinishListing();
        } catch (DukeException e) {
            ui.sayExceptionMessage(e);
        }
    }
}
