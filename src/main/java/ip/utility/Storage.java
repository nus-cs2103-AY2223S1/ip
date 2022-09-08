package ip.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import ip.exception.BadLineFormat;
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
    private final String dataFilePath;
    /** Task data file */
    private final File dataFile;

    /**
     * Constructor to initialize storage with given path.
     *
     * @param specifiedPath Path of the file that storage holds.
     */
    public Storage(String specifiedPath) {
        dataFilePath = specifiedPath;
        dataFile = new File(dataFilePath);
    }

    /**
     * Loads the storage's file into a TaskList.
     *
     * @return The TaskList built from the task data file.
     * @throws IOException If an issue was encountered in opening the file.
     */
    public TaskList getTaskList() throws IOException, BadLineFormat {
        if (!dataFile.exists()) {
            Files.createDirectory(Path.of("data"));
            dataFile.createNewFile();
            return new TaskList();
        }
        TaskList taskListFromStorage = new TaskList();
        String fileContent = new String(Files.readAllBytes(Path.of(dataFilePath)));
        String[] linesInFile = fileContent.split("\\r?\\n");
        for (String line : linesInFile) {
            String[] taskMetadata = line.split("\\|");
            String taskType = taskMetadata[0];
            switch (taskType) {
            case "t":
                taskListFromStorage.add(new ToDo(taskMetadata));
                break;
            case "d":
                taskListFromStorage.add(new Deadline(taskMetadata));
                break;
            case "e":
                taskListFromStorage.add(new Event(taskMetadata));
                break;
            default:
                throw new BadLineFormat(line);
            }
        }
        return taskListFromStorage;
    }

    /**
     * Write data from given TaskList to task data file.
     *
     * @param taskList The TaskList to copy from.
     */
    public void saveTasks(TaskList taskList) {
        try {
            FileWriter destinationFile = new FileWriter(dataFilePath);
            for (Task task : taskList.getTasks()) {
                destinationFile.append(task.formatToSave());
            }
            destinationFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Task data saved in: " + dataFile.getAbsolutePath();
    }

}
