package wanya;

import wanya.task.TaskList;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

// Below code is referenced from
// https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/StorageFile.java

/**
 * Represents a storage to save and load data from hard disk.
 */
public class Storage {
    private final Path path;

    /**
     * Initializes a storage when given a filepath.
     *
     * @param filepath String of the filepath.
     */
    public Storage(String filepath) {
        path = Paths.get(filepath);
    }


    /**
     * Saves the list of tasks to hard disk.
     *
     * @param tasks TaskList that contains list of tasks.
     */
    public void save(TaskList tasks) {
        try {
            Files.write(path, tasks.saveToStorage());
        } catch (IOException e) {
            System.out.println("Oops! Problem encountered! " +
                    "Wanya cannot save the task to hard disk.");
        }
    }

    /**
     * Loads the list of tasks from hard disk.
     *
     * @return encoded String representaton of tasks.
     * @throws WanyaException if file not found in hard disk.
     */
    public List<String> load() throws WanyaException {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new WanyaException("Oops! File not found hehe maybe a spy stole the file awayyy...\n" +
                    "Wanya shall create a new list for you");
        }
    }
}
