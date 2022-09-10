package drake.commands;

import drake.DrakeException;
import drake.Storage;
import drake.TaskList;
import drake.Ui;

import java.io.IOException;

public abstract class CreateTaskCommand extends Command {

    protected String description;

    public CreateTaskCommand(String fullInput) {
        description = fullInput.substring(fullInput.indexOf(' ') + 1);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DrakeException {
        ui.printLine(tasks.getSizeToString());
    }
}
