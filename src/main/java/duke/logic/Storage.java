package duke.logic;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Storage deals with writing task history to a file.
 *
 * @author totsukatomofumi
 */
public class Storage {
    /** Directory where task history file is. */
    private File dataDir;

    /** Task history file. */
    private File history;

    /**
     * Constructs a storage.
     *
     * @param filePath the file path of the stored task history.
     */
    public Storage(String filePath) {
        this.history = new File(filePath);
        this.dataDir = history.getParentFile();
        //when new storage object made, create the storage data files
        this.createRequiredFiles();
    }

    /**
     * Creates the directory and the file.
     */
    public void createRequiredFiles() {
        //will not create if files and directory already exist
        this.dataDir.mkdirs();
        try {
            this.history.createNewFile();
        } catch (IOException e) {
            //file or directory was modified during runtime of this program
            throw new RuntimeException("An important file or directory was modified.");
        }
    }

    /**
     * Updates the file with this task list.
     *
     * @param taskList the task list to update the file with.
     */
    public void update(TaskList taskList) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(history);
            //prepare what to overwrite
            String overwrite = "";
            for (Task task : taskList) {
                overwrite += task.toData() + "\n";
            }
            fileWriter.write(overwrite);
            fileWriter.close();
        } catch (IOException e) {
            //made directory and/or made file could be deleted by user during runtime of this program
            throw new RuntimeException(e);
        }
    }

    /**
     * Clears the file.
     */
    public void clear() {
        try {
            FileWriter clearer = new FileWriter(this.history);
            clearer.write("");
            clearer.close();
        } catch (IOException e) {
            //file or directory was modified during runtime of this program
            throw new RuntimeException("An important file or directory was modified.");
        }
    }

    /**
     * Returns the file task history is stored.
     *
     * @return the file task history is stored.
     */
    public File getHistory() {
        return this.history;
    }
}
