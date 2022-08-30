package ip;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import ip.task.Deadline;
import ip.task.Event;
import ip.task.Task;
import ip.task.ToDo;


/**
 * Encapsulates the task data file stored in hard disk.
 *
 * @author Jonathan Lam
 */
public class Storage {
    /** Path of the task data file */
    private String path;
    /** Task data file */
    private File file;

    /**
     * Constructor to initialize storage with given path.
     *
     * @param path Path of the file that storage holds.
     */
    public Storage(String path) {
        this.path = path;
        file = new File(path);
    }

    /**
     * Loads the storage's file into a TaskList.
     *
     * @return The TaskList built from the task data file.
     * @throws IOException If an issue was encountered in opening the file.
     */
    public TaskList load() throws IOException {
        System.out.println("Searching for existing task data...");
        if (file.createNewFile()) {
            System.out.println("No existing task data. New database initialized.");
            return new TaskList();
        } else {
            System.out.println("Existing task data found. Loading...");
            TaskList taskList = new TaskList();
            String data = new String(Files.readAllBytes(Path.of(path)));
            String[] lines = data.split("\\r?\\n");
            for (String line : lines) {
                String[] info = line.split("\\|");
                String taskType = info[0];
                switch (taskType) {
                case "t":
                    taskList.add(new ToDo(info));
                    break;
                case "d":
                    taskList.add(new Deadline(info));
                    break;
                case "e":
                    taskList.add(new Event(info));
                    break;
                default:
                    System.out.println("Invalid task format found in existing data. Line skipped.");
                }
            }
            System.out.println("Existing data loaded.");
            return taskList;
        }
    }

    /**
     * Loads a task data file from an alternate path.
     * Path is specified by the user.
     *
     * @param altPath The alternate path to source task data from.
     * @return The TaskList built from task data file.
     * @throws IOException If an issue was encountered in opening the file.
     */
    public TaskList load(String altPath) throws IOException {
        this.path = altPath;
        this.file = new File(path);
        return load();
    }

    /**
     * Write data from given TaskList to task data file.
     *
     * @param taskList The TaskList to copy from.
     */
    public void write(TaskList taskList) {
        try {
            FileWriter target = new FileWriter(path);
            for (Task task : taskList.tasks) {
                target.append(task.writeFormat());
            }
            target.close();
            System.out.println("Current task list successfully saved.");
        } catch (IOException e) {
            System.out.println("Error in writing file.");
            e.printStackTrace();
        }
    }

    /**
     * Gets the absolute path to the current file that Storage holds.
     *
     * @return Absolute path of file.
     */
    public String getPath() {
        return file.getAbsolutePath();
    }

    /**
     * Deletes the file that Storage holds.
     */
    public void wipe() {
        file.delete();
    }

    @Override
    public String toString() {
        return "File containing task data saved at \"" + path + "\".";
    }
}
