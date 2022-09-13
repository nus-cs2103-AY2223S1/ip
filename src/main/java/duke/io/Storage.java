package duke.io;

import java.io.File;
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

    private static final File FILE_PATH = new File("data/duke.txt");
    private static final String FILE_NAME = "duke.txt";
    private static final String FILE_FOLDER = "data";

    /**
     * Loads data from a save file.
     *
     * @return A TaskList with data loaded from a savefile.
     */
    public static TaskList readData() throws DukeException {
        try {
            Scanner fileScanner = new Scanner(FILE_PATH);
            TaskList tasks = new TaskList();
            while (fileScanner.hasNextLine()) {
                String[] info = fileScanner.nextLine().split(" \\| ");
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
        } catch (IOException e) {
            createNewStorageFile();
            throw new DukeException("data cannot be found, making new folder");
        } catch (DukeException e) {
            System.out.println("data cannot be read");
        }
        return null;
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

    private static void createNewStorageFile() throws DukeException {
        try {
            File folder = new File(FILE_FOLDER);
            folder.mkdir();
            File file = new File(folder, FILE_NAME);
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("folder cannot be created");
        }
    }
}
