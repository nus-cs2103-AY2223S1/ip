package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class WelcomeCommand extends Command {
    public WelcomeCommand() {
        super(CommandType.WELCOME);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printWelcomeMessage();
    }
}

