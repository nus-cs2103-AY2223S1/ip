package Commands;

import DataStruct.TaskList;
import DaveExceptions.DaveException;
import Storage.ArchiveHandler;

import java.util.Objects;

public class ArchiveCommand extends Command{

    private TaskList tasks;
    private String name = null;
    private ArchiveHandler archive;

    /**
     * Initialises an archive command with a provided tasklist and
     * the name of the archive
     *
     * @param tasks Tasklist to be archived
     * @param name the name of the archive
     */
    public ArchiveCommand(TaskList tasks, String name) throws DaveException {
        this.tasks = tasks;
        this.name = name;
        ArchiveHandler newSave = new ArchiveHandler();
        newSave.init();
        this.archive = newSave;
    }

    /**
     * Executes the command. Returns a list of archives to view if no name is supplied.
     * Else, saves the current task list to the given name.
     *
     * @return The string representation of the result of the command.
     */
    @Override
    public String execute() throws DaveException {
        if (Objects.equals(name, "")) {
            return archive.toString();
        } else {
            archive.archive(tasks, name);
            return String.format(
                    "The current list of tasks has been archived under %s, master!",
                    this.name
            );
        }
    }
}
