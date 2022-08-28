package duke.util;

import duke.DukeException;
import duke.task.TaskList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class to save and load users' TaskList object.
 *
 * @author Kavan
 */
public class StoredTasks {
    private String fileDir;
    private String filePath;

    /**
     * Constructor for StoredTasks class.
     *
     * @param fileDir Target File Directory.
     * @param filePath Target File Path.
     */
    public StoredTasks(String fileDir, String filePath) {
        this.fileDir = fileDir;
        this.filePath = filePath;
    }

    /**
     * Returns saved-data.
     *
     * @return Saved-data stored as BufferedReader object.
     * @throws DukeException If IOException is caught.
     */
    public BufferedReader load() throws DukeException {
        BufferedReader br;
        try {
            File dir = new File(this.fileDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File fileName = new File(this.filePath);
            if (!fileName.exists()) {
                fileName.createNewFile();
            }
            br = new BufferedReader(new FileReader(fileName));
        } catch (IOException e) {
            throw new DukeException("Failure in reading file, creating new save file");
        }
        return br;
    }

    /**
     * Saves users' TaskList as a text file.
     *
     * @param storedTasks TaskList Object.
     */
    public void save(TaskList storedTasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(storedTasks.taskListToSaveString());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
