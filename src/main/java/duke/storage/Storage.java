package duke.storage;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.common.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Deals with loading tasks from the save file and saving tasks in the save file.
 *
 * @author Rama Aryasuta Pangestu
 */
public class Storage {
    private final File saveFile;

    /**
     * Constructs a helper tool to load and save tasks to the specified file.
     *
     * @param filePath the path of the specified file.
     */
    public Storage(String filePath) {
        this.saveFile = new File(filePath);
    }

    private void createSaveFile(File saveFile) throws DukeException {
        if (!saveFile.exists()) {
            try {
                saveFile.getParentFile().mkdir();
                saveFile.createNewFile();
            } catch (java.io.IOException exception) {
                throw new DukeException("I/O error occurs");
            } catch (SecurityException exception) {
                throw new DukeException("No write access");
            }
        }
    }

    private Scanner getInputScanner(File saveFile) throws DukeException {
        createSaveFile(saveFile);
        try {
            Scanner scanner = new Scanner(saveFile);
            return scanner;
        } catch (java.io.FileNotFoundException exception) {
            throw new DukeException(
                    "Save file not found, even after duke.Duke tries to create one :(");
        }
    }

    private PrintWriter getOutputWriter(File saveFile) throws DukeException {
        createSaveFile(saveFile);
        try {
            PrintWriter writer = new PrintWriter(saveFile);
            return writer;
        } catch (java.io.FileNotFoundException exception) {
            throw new DukeException(
                    "Save file not found, even after duke.Duke tries to create one :(");
        }
    }

    /**
     * Returns the tasks saved in the specified save file.
     *
     * @return tasks saved in the file
     * @throws DukeException if an IO error occurs
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner scanner = getInputScanner(this.saveFile);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            try {
                tasks.add(Task.decode(input));
            } catch (DukeException exception) {
                // ignore invalid line in save file
            }
        }
        return tasks;
    }

    /**
     * Saves the tasks in the task list to the save file
     *
     * @param taskList the task list
     * @throws DukeException if an IO error occurs
     */
    public void save(TaskList taskList) throws DukeException {
        ArrayList<Task> tasks = taskList.getTasks();
        PrintWriter writer = getOutputWriter(this.saveFile);
        for (int i = 0; i < tasks.size(); i += 1) {
            Task task = tasks.get(i);
            writer.println(task.encode());
        }
        writer.close();
    }
}
