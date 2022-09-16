package duke.command;

import static duke.ui.Messages.EXIT_MESSAGE;

import duke.data.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Exit Command
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return EXIT_MESSAGE;
    }
}
