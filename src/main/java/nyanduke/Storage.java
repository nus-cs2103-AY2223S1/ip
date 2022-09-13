package nyanduke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import nyanduke.task.Task;
import nyanduke.task.TaskList;

/**
 * The Storage class deals with loading tasks from a file
 * and saving tasks in the file.
 */
public class Storage {
    /** The location of the file in the hard disk to load from and write to. */
    private final String filePath;

    /**
     * Constructs a new Storage that loads tasks from a specified file path.
     *
     * @param filePath The specified file path to load tasks from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads data from the NyanDuke data file on the hard disk.
     *
     * @return An array list of tasks stored in the data file on the hard disk.
     * @throws NyanDukeException when unable to load data from the hard disk.
     */
    public ArrayList<Task> load() throws NyanDukeException {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            Scanner sc = new Scanner(f);
            ArrayList<Task> tasks = new ArrayList<>();
            while (sc.hasNextLine()) {
                tasks.add(Task.parseData(sc.nextLine()));
            }
            return tasks;
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            throw new NyanDukeException("Unable to load data from hard disk file.");
        }
    }

    /**
     * Saves the task list as data in a data file on the hard disk.
     *
     * @param tasks The specified TaskList to write into a data file.
     * @throws NyanDukeException when unable to write into the hard disk file.
     */
    public void writeToFile(TaskList tasks) throws NyanDukeException {
        assert tasks != null : "Storage::writeToFile invoked with null argument.";
        try {
            FileWriter fw = new FileWriter(filePath);
            String[] dataStrings = tasks.convertAllToData();
            for (String s : dataStrings) {
                fw.write(s);
            }
            fw.close();
        } catch (IOException e) {
            throw new NyanDukeException("Unable to write into the hard disk file.");
        }
    }
}
