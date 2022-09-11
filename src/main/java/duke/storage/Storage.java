package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.parser.ParseException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Storage class for storing and retrieving data from a file.
 */
public class Storage {

    private final File dataFile;

    /**
     * Constructs a new Storage object with a given file path.
     *
     * @param path The path to the file to store data in.
     */
    public Storage(Path path) {
        this.dataFile = new File(path.toUri());
    }

    /**
     * Ensures that the {@code dataFile} is there and accessible.
     * If not then it creates the file.
     * If it could not be created then it throws an exception.
     */
    private void ensureDataFileExists() {
        if (dataFile.exists()) {
            return;
        }
        // Ref: https://stackoverflow.com/a/4040667
        File parent = dataFile.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            throw new DukeException("Could not create directory: " + parent);
        }
        try {
            if (!dataFile.createNewFile()) {
                throw new DukeException("Could not create file: " + dataFile);
            }
        } catch (IOException e) {
            throw new DukeException("An I/O error occurred creating the file: " + dataFile);
        }
    }

    /**
     * Loads the {@code Task}s from the {@code dataFile}.
     *
     * @return The {@code Task}s loaded from the {@code dataFile} in an {@code ArrayList}.
     */
    public ArrayList<Task> load() {
        Scanner scanner = getScanner();
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            try {
                tasks.add(Parser.parseTask(line));
            } catch (ParseException e) {
                throw new DataFileCorruptedException();
            }
        }
        return tasks;
    }

    /**
     * Returns a {@code Scanner} for the {@code dataFile}.
     * Throws an exception if the {@code dataFile} does not exist or could not be opened.
     *
     * @return The {@code Scanner} for the {@code dataFile}.
     */
    private Scanner getScanner() {
        ensureDataFileExists();
        Scanner scanner;
        try {
            scanner = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            throw new DukeException("Could not open datafile for reading: " + dataFile);
        }
        return scanner;
    }

    /**
     * Saves the {@code Task}s from a {@code TaskList} to the {@code dataFile}.
     *
     * @param tasks The {@code TaskList} to save.
     */
    public void save(TaskList tasks) {
        ensureDataFileExists();
        try {
            FileWriter fw = new FileWriter(dataFile);
            for (Task task : tasks) {
                fw.write(task.toSaveString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Could not write to file: " + dataFile);
        }
    }
}
