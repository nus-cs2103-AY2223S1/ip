package seedu.duke;

import seedu.duke.Task.Task;
import seedu.duke.Task.TaskSorter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a storage, stores and read data from given file location
 */
public class Storage {
    protected Path filePath;

    /**
     * Instantiates a new Storage object
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes the contents of a given TaskList to a specified file
     *
     * @param tasks the TaskList containing Task to be written to file
     */
    public void writeToFile(TaskList tasks) throws IOException {
        //structure: command|1 (1 for mark, 0 for unmark)
        ArrayList<Task> taskArr = tasks.getTasks();
        ArrayList<String> stringArr = new ArrayList<String>();
        for (Task t: tasks.getTasks()) {
            stringArr.add(t.getData());
        }

        Iterable<String> lines = stringArr;
        try {
            Files.createDirectories(this.filePath.getParent());
            Files.write(this.filePath, stringArr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the contents of a given TaskList from a specified file
     *
     * @param tasks the TaskList to contain Tasks loaded from a specified file
     */
    public void loadFromFile(TaskList tasks) {
        try {
            if (!Files.exists(this.filePath)) {
                Files.createDirectories(this.filePath.getParent());
                Files.createFile(this.filePath);
            }

            List<String> data = Files.readAllLines(this.filePath);

            for (String s: data) {
                tasks.add(TaskSorter.sortTaskFromData(s));
            }

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
