package jenny.commands;

import jenny.exceptions.JennyException;
import jenny.storage.TaskStorage;
import jenny.tasks.AbstractTask;

import java.util.ArrayList;

public class LoadCommand extends AbstractCommand {
    public static final String COMMAND = "load";

    public LoadCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void run(ArrayList<AbstractTask> tasks) throws JennyException {
        TaskStorage<ArrayList<AbstractTask>> taskStorage = new TaskStorage<>("tasks.txt");
        tasks = taskStorage.load();
    }
}
