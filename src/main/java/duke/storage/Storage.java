package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.InputOutputException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Class dealing with storage of tasks.
 */
public class Storage {

    private final String directory;
    private final String fileName;
    private File data;

    /**
     * Initialises Storage object, creating specified file.
     *
     * @param directory Directory that the data file is located in.
     * @param fileName  Filename of file to be loaded/saved to.
     * @throws DukeException Exception thrown in case of error creating file/directory.
     */
    public Storage(String directory, String fileName) throws DukeException {
        this.directory = directory;
        this.fileName = fileName;
        data = createFileWithDirIfNotExist(directory, fileName);
    }

    private File createFileWithDirIfNotExist(String directory, String fileName) throws DukeException {
        File f = new File(directory + fileName);
        try {
            f.getParentFile().mkdirs();
            f.createNewFile();
        } catch (IOException e) {
            throw new InputOutputException("Error finding/creating data file");
        }
        return f;
    }

    /**
     * Returns list of strings of tasks in TaskList.
     *
     * @return List of strings corresponding to tasks in TaskList
     * @throws DukeException
     */
    public ArrayList<String> load() throws DukeException {
        ArrayList<String> toReturn = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(data));
            String line = reader.readLine();
            while (line != null) {
                toReturn.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            throw new InputOutputException("Error reading from data file: " + e.getMessage());
        }
        return toReturn;
    }

    private ArrayList<String> tasksToString(TaskList tasks) {
        ArrayList<String> tasksStrings = new ArrayList<>();

        for (Task currTask : tasks.get()) {
            tasksStrings.add(currTask.getSaveString());
        }

        return tasksStrings;
    }

    /**
     * Saves specified tasks to file that object was initialised with.
     *
     * @param tasks Tasks to be saved to file
     */
    public void saveToFile(TaskList tasks) {
        try {
            data.delete();
            data = createFileWithDirIfNotExist(directory, fileName);

            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(data));
            ArrayList<String> toWrite = tasksToString(tasks);

            for (String str : toWrite) {
                writer.write(str + System.lineSeparator());
            }

            writer.close();
        } catch (Exception e) {
            throw new DukeException("Failed to save to file");
        }
    }
}
