package jenny.commands;

import jenny.exceptions.JennyException;
import jenny.storage.TaskStorage;
import jenny.tasks.AbstractTask;

import java.util.ArrayList;

public class SaveCommand extends AbstractCommand {
    private static final String MESSAGE_SCOPE = SaveCommand.class.getSimpleName();
    public static final String COMMAND = "save";

    public SaveCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void run(ArrayList<AbstractTask> tasks) throws JennyException {
        TaskStorage<ArrayList<AbstractTask>> taskStorage = new TaskStorage<>("tasks.txt");
        taskStorage.save(tasks);
    }
}
