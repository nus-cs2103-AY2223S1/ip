package Commands;

import DataStruct.TaskList;
import DaveExceptions.DaveException;
import Storage.ArchiveHandler;

import java.util.Objects;

public class ArchiveCommand extends Command{

    private TaskList tasks;
    private String name = null;
    private ArchiveHandler archive;

    public ArchiveCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    public ArchiveCommand(TaskList tasks, String name) throws DaveException {
        this.tasks = tasks;
        this.name = name;
        ArchiveHandler newSave = new ArchiveHandler();
        newSave.init();
        this.archive = newSave;
    }

    @Override
    public String execute() throws DaveException {
        if (Objects.equals(name, "")) {
            return archive.toString();
        } else {
            archive.archive(tasks, name);
            return String.format(
                    "The current list of tasks has been archived under %s!",
                    this.name
            );
        }
    }
}
