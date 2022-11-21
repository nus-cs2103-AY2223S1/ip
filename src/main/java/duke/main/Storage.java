package duke.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import duke.task.Formatting;
import duke.task.Task;

/**
 * A class to handle the storage.
 */
public class Storage {
    protected ArrayList<Task> arr;
    protected String filePath;

    private Formatting f = new Formatting();

    /**
     * Constructs the storage.
     *
     * @param filePath the directory of the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Loads file of Tasks into an ArrayList.
     *
     * @return an ArrayList of Tasks
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("Cannot load data!");
        }
        arr = FileManipulation.read(filePath);
        return this.arr;
    }
    /**
     * Overwrites current data file with the new updates.
     */
    public void overwrite() {
        FileManipulation.overwrite(this.filePath, this.f.apply(this.arr));
    }
}
