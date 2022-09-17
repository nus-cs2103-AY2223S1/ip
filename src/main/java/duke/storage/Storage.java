package duke.storage;

import duke.exceptions.DukeException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.storage.StorageReader.fileLineToTask;

/**
 * A class for storing and retrieving save data for Duke.
 */
public class Storage {
    File file;

    /**
     * Constructs a Storage.
     *
     * @param filePath String filepath of the save data.
     */
    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Loads the tasks from the save data.
     *
     * @return ArrayList of tasks retrieved from the save data.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> res = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            assert this.file.exists();
            while (sc.hasNext()) {
                res.add(fileLineToTask(sc.nextLine()));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            try {
                Files.createDirectories(Paths.get("./data"));
                File f = new File("data/tasks.txt");
            } catch (IOException ex) {
                throw new DukeException("Failed to load tasks!");
            }
        }
        return res;
    }

    /**
     * Refreshes the save data.
     *
     * @param tasks TaskList to obtain new data from.
     */
    public void refresh(TaskList tasks) {
        File temp = new File("data/temp.txt");
        try {
            FileWriter fw = new FileWriter(temp);
            fw.write(tasks.toFileFormat());
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        file.delete();
        temp.renameTo(new File("data/tasks.txt"));
        file = temp;
    }

    /**
     * Adds a new task to the save data.
     *
     * @param task Task to be added to the save data.
     */
    public void addTaskToStorage(Task task) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(task.toFileFormat() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
