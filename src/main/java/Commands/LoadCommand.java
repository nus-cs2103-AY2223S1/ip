package Commands;

import DataStruct.TaskList;
import DaveExceptions.DaveException;
import Storage.ArchiveHandler;

import java.util.Objects;

public class LoadCommand extends Command{

    private TaskList tasks;
    private String name;
    private ArchiveHandler archive;

    /**
     * Initialises a load command with a provided name to an archive and a tasklist to add to
     *
     * @param tasks Tasklist to load the archive to
     * @param name name of the archive to be loaded
     */
    public LoadCommand(TaskList tasks, String name) throws DaveException {
        this.tasks = tasks;
        this.name = name;
        ArchiveHandler newSave = new ArchiveHandler();
        newSave.init();
        this.archive = newSave;
    }

    /**
     * Executes the command.
     * @return The string representation of the result of the command.
     */
    @Override
    public String execute() throws DaveException {
        if (Objects.equals(name, "")) {
            return "Please enter a valid archive to load!";
        } else {
            archive.loadArchive(tasks, name);
            return String.format(
                    "The archive %s has been loaded to the current task list!",
                    this.name
            );
        }
    }
}
