package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.storage.Storage;

/**
 * This class represents the list command that returns the current task list
 * kept by Duke.
 */
public class ListCommand extends Command{

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.indentResponse(tasks.toString());
    }
}
