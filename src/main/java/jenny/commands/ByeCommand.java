package jenny.commands;

import jenny.exceptions.JennyException;
import jenny.storage.Storage;
import jenny.tasks.Task;
import jenny.tasks.TaskList;
import jenny.util.Ui;

import java.util.ArrayList;

public class ByeCommand extends Command {
    public static final String COMMAND = "bye";

    public ByeCommand(String arguments) {
        super(arguments);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage<ArrayList<Task>> storage) throws JennyException {
        ui.exit();
    }
}
