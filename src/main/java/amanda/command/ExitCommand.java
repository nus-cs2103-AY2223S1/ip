package amanda.command;

import amanda.manager.*;
import amanda.ui.*;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(null, 0);
    }

    @Override
    public void execute(TaskManager tasks, Ui ui, StoreManager store) {
        ui.showExitCommand();
    }
}
