package duke.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.DukeException;
import duke.types.Deadline;
import duke.types.Event;
import duke.types.Task;
import duke.types.Todo;

/**
 * Storage for the Duke chatbot, storing and restoring files into/from a save file.
 *
 * @author Aaron Tan
 */
public class Storage {

    private static final String FILE_PATH = "data/duke.txt";
    private static final String FILE_NAME = "duke.txt";
    private static final String FILE_FOLDER = "data";
    private File file;

    /**
     * Creates a storage object of the specified file path.
     *
     * @param filePath Path from root directory to the file.
     * @throws DukeException If unable to create file.
     */
    public Storage() throws DukeException {
        file = new File(FILE_PATH);
        try {
            // Does not overwrite directory or file if it already exists.
            file.getParentFile().mkdir();
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Directory or file cannot be located. New file is created.");
        }
    }

    /**
     * Loads data from a save file.
     *
     * @return A TaskList with data loaded from a savefile.
     */
    public TaskList readData() throws DukeException {
        try {
            Scanner fileScanner = new Scanner(file);
            TaskList tasks = new TaskList();
            while (fileScanner.hasNextLine()) {
                String[] info = fileScanner.nextLine().split(" \\| ");
                if (info.length < 2) {
                    throw new DukeException("data cannot be read");
                }
                String type = info[0];
                boolean isDone = info[1].equals("1") ? true : false;
                String description = info[2];
                Task task;
                switch (type) {
                case "T":
                    task = new Todo(description, isDone);
                    break;
                case "D":
                    task = new Deadline(description, isDone, info[3]);
                    break;
                case "E":
                    task = new Event(description, isDone, info[3]);
                    break;
                default:
                    throw new DukeException("invalid task type, try T, D, E");
                }
                tasks.add(task);
            }
            fileScanner.close();
            return tasks;
        } catch (DukeException | FileNotFoundException e) {
            throw new DukeException("File is inaccessible");
        }
    }

    /**
     * Saves data from given TaskList into save file.
     *
     * @param tasks TaskList to be saved.
     */
    public void saveData(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String toSave = task.saveString();
                fw.write(toSave);
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("data cannot be saved");
        }
    }

    private void createNewStorageFile() throws DukeException {
        try {
            File folder = new File(FILE_FOLDER);
            folder.mkdir();
            File file = new File(folder, FILE_NAME);
            file.createNewFile();
            readData();
        } catch (IOException e) {
            throw new DukeException("folder cannot be created");
        }
    }
}
