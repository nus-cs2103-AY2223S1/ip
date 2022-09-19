package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import exception.DorisException;
import parser.Parser;
import task.Task;
import task.TaskList;

/**
 * Encapsulates the saving and loading of tasks to and from the local file saved in the hard drive.
 *
 * @author Marcus Low
 */
public class Storage {

    private final File storageFile;

    /**
     * Constructs a Storage class to store the local file saved in the hard drive
     *
     * @param filePath Path of the local file saved in the hard drive.
     */
    public Storage(String filePath) {
        this.storageFile = new File(filePath);
    }

    /**
     * Reads from the local file and converts the saved data into a list of tasks, saved into a task list.
     * If the local file does not exist, create it.
     *
     * @return The task list storing the list of tasks.
     * @throws DorisException If there is an issue loading the file.
     */
    public ArrayList<Task> load() throws DorisException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(storageFile);
            while (sc.hasNextLine()) {
                Task task = Parser.parseSaved(sc.nextLine());
                tasks.add(task);
            }
            Task task = Parser.parseSaved(sc.nextLine());
            tasks.add(task);
        } catch (FileNotFoundException e) {
            storageFile.getParentFile().mkdirs();
            storageFile.createNewFile();
        } finally {
            return tasks;
        }
    }

    /**
     * Writes the task list to the local file.
     *
     * @param tasks The task list to be saved to the local file.
     * @throws DorisException If there is an issue saving the file.
     */
    public void save(TaskList tasks) throws DorisException {
        try {
            File taskFile = new File(String.valueOf(storageFile));
            PrintWriter taskFileWriter = new PrintWriter(new FileWriter(taskFile));
            for (int i = 0; i < TaskList.size(); i++) {
                taskFileWriter.write(TaskList.get(i).toStringText() + "\n");
            }
            taskFileWriter.close();
        } catch (IOException e) {
            throw new DorisException(e.toString());
        }
    }
}
