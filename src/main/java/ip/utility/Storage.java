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
 */
public class Storage {
    /** Path of the task data file */
    private final String dataFilePath;
    private final String backupFilePath;
    /** Task data file */
    private final File dataFile;
    private final File backupFile;

    /**
     * Constructor to initialize storage with given path.
     *
     * @param specifiedPath Path of the file that storage holds.
     */
    public Storage(String specifiedPath) {
        dataFilePath = specifiedPath + "/taskData.txt";
        backupFilePath = specifiedPath + "/backupFile.txt";
        dataFile = new File(dataFilePath);
        backupFile = new File(backupFilePath);
        initializeFiles();
    }

    private void initializeFiles() {
        try {
            if (!new File("data/").isDirectory()) {
                Files.createDirectory(Path.of("data"));
            }
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
            if (!backupFile.exists()) {
                backupFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TaskList getLatestTaskList() {
        return getTaskList(false);
    }

    public TaskList getBackupTaskList() {
        return getTaskList(true);
    }

    public void saveToLatest(TaskList taskList) {
        saveTasks(taskList, false);
    }

    public void saveToBackup(TaskList taskList) {
        saveTasks(taskList, true);
    }

    /**
     * Loads the saved file into a TaskList.
     *
     * @param isFromBackup Whether to load the latest file or backup file.
     * @return The TaskList built from the task data file.
     */
    private TaskList getTaskList(boolean isFromBackup) {
        String filePath = isFromBackup ? backupFilePath : dataFilePath;
        try {
            TaskList taskListFromStorage = new TaskList();
            String fileContent = new String(Files.readAllBytes(Path.of(filePath)));
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
        } catch (IOException | BadLineFormat e) {
            e.printStackTrace();
            return new TaskList();
        }
    }

    /**
     * Writes data from given TaskList to task data file.
     *
     * @param isToBackup Whether to save to the latest file or backup file.
     * @param taskList The TaskList to copy from.
     */
    private void saveTasks(TaskList taskList, boolean isToBackup) {
        String filePath = isToBackup ? backupFilePath : dataFilePath;
        try {
            FileWriter destinationFile = new FileWriter(filePath);
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
