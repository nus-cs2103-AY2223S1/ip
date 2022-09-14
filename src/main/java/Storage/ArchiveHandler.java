package Storage;

import DataStruct.TaskList;
import DaveExceptions.DaveException;

import java.io.File;
import java.util.HashSet;
import java.util.List;

public class ArchiveHandler extends SaveHandler{

    private HashSet<String> archive;

    private final String CURR_DIR = System.getProperty("user.dir");

    protected final String ARCHIVE_DIR = CURR_DIR + "/archive";

    private final File ARCHIVE_FILE = new File(ARCHIVE_DIR);

    /**
     * Initialises the archival file handler. Ensures that the archival file exists by creating it if necessary,
     * and checks that the file is able to be read and written to.
     *
     * @throws DaveException Exception is thrown if program is unable to read and/or write to archival file.
     */
    public void init() throws DaveException {
        ARCHIVE_FILE.mkdirs();
        if (!ARCHIVE_FILE.canRead() || !ARCHIVE_FILE.canWrite()) {
            throw new DaveException("Oh no! You do not have permission to read and/or write to the archive :(");
        }

        String[] archiveList = ARCHIVE_FILE.list();

//        for (String fileName : archiveList) {
//            fileName = fileName.replaceAll(".txt", "");
//        }

        archive = new HashSet<String>(List.of(archiveList));
    }

    /**
     * Saves the given task list to the save file.
     * Adapted from https://stackoverflow.com/questions/16111496/
     *     java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the
     *
     * @param tasks Tasklist object to be saved to the save file.
     * @throws DaveException Exception is thrown if program is unable to save the task list to the file.
     */
    public void archive(TaskList tasks, String name) throws DaveException {
        if (tasks.size() == 0) {
            throw new DaveException("Please give me some tasks to archive uwu!");
        }
        String archiveName = ARCHIVE_DIR + "/" + name + ".txt";
        File archiveFile = new File(archiveName);
        initFile(archiveFile);
        write(tasks, archiveFile);
        archive.add(name);
    }

    /**
     * Loads a task list from the save file.
     * Adapted from https://stackoverflow.com/questions/16111496/
     *     java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the
     *
     * @return tasks Tasklist object to read from the save file.
     * @throws DaveException Exception is thrown if program is unable to read the task list from the file.
     */
    public void loadArchive(TaskList tasks, String name) throws DaveException {
        String archiveName = ARCHIVE_DIR + "/" + name + ".txt";
        File archiveFile = new File(archiveName);
        initFile(archiveFile);
        TaskList newTasks = read(archiveFile);
        if (newTasks.size() == 0) {
            throw new DaveException("The archive you are looking for cannot be found :(");
        }
        tasks.append(newTasks);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Here is your archive:\n");
        int count = 1;
        for (String i : archive) {
            result.append(String.format("%d. %s \n", count, i.substring(0, i.lastIndexOf('.'))));
            count++;
        }
        return result.toString();
    }
}
