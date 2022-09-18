package drake.commands;

import java.io.IOException;
import java.util.List;

import drake.DrakeException;
import drake.Storage;
import drake.TaskList;
import drake.Ui;

/**
 * The bye command.
 */
public class ByeCommand extends Command {
    @Override
    public List<String> execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DrakeException {
        return List.of(ui.replyBye());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
