package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

public class ExitCommand extends Command {
    private static final ExitCommand EXIT_COMMAND = new ExitCommand();

    private ExitCommand() {
        super(true);
    }

    public static ExitCommand of() {
        return EXIT_COMMAND;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.showGoodbye();
    }
}
