package duke.commands;

import duke.exceptions.DukeException;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

public class ListCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.list();
            for (int i = 0; i < taskList.getSize(); i++) {
                ui.showTaskWithIndex(taskList.getTask(i), i);
            }
            ui.finishListing();
        } catch (DukeException e) {
            ui.sayExceptionMessage(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ListCommand) {
            return true;
        }
        return false;
    }
}
