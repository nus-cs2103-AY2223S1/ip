package duke.command;

import java.io.File;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Archives the current task list into a file and clears it afterwards.
 */
public class ArchiveCommand extends Command {

    public static final String COMMAND_NAME = "archive";

    private File file;
    private boolean canOverwrite;

    /**
     * Constructs an ArchiveCommand that stores into data/archive.txt and
     * is not allowed to overwrite the file if it exists.
     */
    public ArchiveCommand() {
        file = new File("data/archive.txt");
        canOverwrite = false;
    }

    /**
     * Constructs an ArchiveCommand that stores into data/archive.txt with a
     * boolean indicating whether it is allowed to overwrite the file or not.
     *
     * @param canOverwrite if true, file can be overwritten if it exists.
     */
    public ArchiveCommand(boolean canOverwrite) {
        file = new File("data/archive.txt");
        this.canOverwrite = canOverwrite;
    }

    /**
     * Constructs an ArchiveCommand that stores into the specified file path with a
     * boolean indicating whether it is allowed to overwrite the file or not.
     *
     * @param filePath the file path to archive the task list to.
     * @param canOverwrite if true, file can be overwritten if it exists.
     */
    public ArchiveCommand(String filePath, boolean canOverwrite) {
        file = new File(filePath);
        this.canOverwrite = canOverwrite;
    }


    /**
     * Archives the specified TaskList object into a file. Afterwards, it clears
     * the task list from the specified Storage object.
     *
     * @param tasks the specified TaskList.
     * @param ui the Ui where Duke's responses are recorded.
     * @param storage the specified Storage.
     */
    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (file.exists() && !canOverwrite) {
            throw new DukeException(String.format("There exists a file in %s.\n"
                    + "If you'd like to overwrite this file, please add \"/force\" to the command", file.getPath()));
        }
        Storage archiveStorage = new Storage(file);
        archiveStorage.save(tasks);

        tasks.clear();
        storage.save(tasks);

        ui.showReply(
                String.format("Your tasks are now archived in %s. The tasklist have been cleared.\n"
                        + "To recover the archived tasklist, you may copy the archived file to %s and restart Duke.",
                        file.getPath(), storage.getFilePath()));
    }

    @Override
    public boolean isTerminator() {
        return false;
    }
}
