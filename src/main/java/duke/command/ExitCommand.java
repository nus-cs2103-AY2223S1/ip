package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    /*
     * Duke exits
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        toggleExit();
        return ui.showBye();
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ExitCommand) {
            return true;
        }
        return false;
    }
}