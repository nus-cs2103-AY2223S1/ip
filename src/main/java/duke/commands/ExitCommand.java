package duke.commands;

import duke.Response;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * A special command that exits the application.
 */
public class ExitCommand extends Command {
    @Override
    public Response execute(TaskList taskList, Storage storage) {
        return new Response(TextUi.GOODBYE_MESSAGE);
    }
}
