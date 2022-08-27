package duke;

import duke.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Deals with loading <code>Tasks</code> from storage file and saving <code>Tasks</code> in the file.
 */
public class Storage {
    private static final String FILENAME = "saved-tasks.txt";
    private final File dataFile;

    /**
     * Constructs a storage containing a data file to store tasks.
     *
     * @param filePath directory to save tasks
     * @throws DukeException if unable to create a data file
     */
    public Storage(String filePath) throws DukeException {
        try {
            File fileDir = new File(filePath);
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }
            this.dataFile = new File(filePath + "/" + FILENAME);
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("Unable to create data file!");
        }
    }

    /**
     * Stores the list of tasks to a file.
     *
     * @param tasks list of stored tasks
     */
    public void saveData(TaskList tasks) {
        String csv = tasks.toCsv();
        try {
            FileWriter writer = new FileWriter(this.dataFile);
            writer.write(csv);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the file with stored tasks.
     *
     * @return file with stored tasks
     * @throws DukeException if unable to load data file
     */
    public File load() throws DukeException {
        if (!dataFile.exists()) {
            throw new DukeException("File loading error.");
        }
        return dataFile;
    }
}
