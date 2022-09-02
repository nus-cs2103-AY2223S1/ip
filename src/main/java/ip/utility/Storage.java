package ip.utility;

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
    private final File file;

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
        if (file.exists()) {
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
                    // Do nothing
                }
            }
            return taskList;
        } else {
            Files.createDirectory(Path.of("data"));
            file.createNewFile();
            return new TaskList();
        }
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "File containing task data saved at \"" + path + "\".";
    }
}
