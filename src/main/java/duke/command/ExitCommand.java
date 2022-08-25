package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ExitCommand closes the Duke chatbot.
 */
public class ExitCommand extends Command {

    /**
     * Constructor for ExitCOmmand.
     */
    public ExitCommand() {
        super();
        this.isExit = true;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodByeMessage();
    }
}
