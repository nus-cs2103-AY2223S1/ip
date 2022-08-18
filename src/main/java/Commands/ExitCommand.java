package Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage storage) {
        super.setExit();
        ui.printBye();
    }

}
