package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a Storage to load and save the data of Duke.
 */
public class Storage {
    protected String path;

    /**
     * Constructor of Storage with path name to the Storage file.
     *
     * @param path Path name to the Storage file.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Returns a TaskList which results from loading the data in the Storage file.
     *
     * @return TaskList from the data in the Storage file.
     * @throws DukeException If storage data can not be parsed.
     */
    public TaskList load() throws DukeException {
        File file = new File(path);
        TaskList tasks = new TaskList();
        Scanner sc;
        try {
            file.createNewFile();
            sc = new Scanner(file);
        } catch (IOException e) {
            return tasks;
        }
        while (sc.hasNext()) {
            tasks.add(Parser.parseStorageTask(sc.nextLine()));
        }
        return tasks;
    }

    /**
     * Saves the current Duke data to the Storage file.
     *
     * @param tasks TaskList which represents the current Duke data.
     * @throws DukeException If save is unsuccessful.
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(path);
            fw.write(tasks.toData());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Save unsuccessful! " + e.getMessage());
        }
    }
}
