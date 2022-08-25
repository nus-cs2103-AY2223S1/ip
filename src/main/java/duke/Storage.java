package duke;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Storage is a class to load and save tasks.
 */
public class Storage {
    private Path relativeDirectoryPath;
    private Path relativeFilePath;
    private Path filepath;

    /**
     * Constructor for Storage.
     * @param projectRoot filepath for storage.
     */
    public Storage(String projectRoot) {
        this.relativeDirectoryPath = Paths.get(projectRoot, "data");
        this.relativeFilePath = Paths.get(projectRoot, "data", "duke.txt");
        this.filepath = Paths.get(projectRoot);
    }

    /**
     * Returns whether a directory is present.
     * @return true or false if directory is present.
     */
    public boolean isDirectoryPresent() {
        return Files.exists(relativeDirectoryPath);
    }

    /**
     * Returns whether a file is present.
     * @return true or false if file is present.
     */
    public boolean isFilePresent() {
        return Files.exists(relativeFilePath);
    }

    /**
     * Creates a directory.
     * @throws IOException
     */
    public void createDirectory() throws IOException {
        Files.createDirectory(relativeDirectoryPath);
    }

    /**
     * Creates a file.
     * @throws IOException
     */
    public void createFile() throws IOException {
        Files.createFile(relativeFilePath);
    }

    /**
     * Returns a task that is parsed from string form.
     * @param details details of the task.
     * @param taskType type of task.
     * @return task object.
     */
    public Task parseStringToTask(String details, String taskType) {
        Task task = null;
        switch (taskType) {
            case "T": {
                String[] taskDetails = details.split(" \\| ");
                task = new ToDo(taskDetails[1]);
                if (Integer.parseInt(taskDetails[0]) == 1) {
                    task.markAsDone();
                }
                break;
            }
            case "D":
            case "E": {
                String[] taskDetails = details.split(" \\| ");
                task = new Deadline(taskDetails[1], taskDetails[2]);
                if (Integer.parseInt(taskDetails[0]) == 1) {
                    task.markAsDone();
                }
                break;
            }
            default:
                break;
        }
        return task;
    }

    /**
     * Returns an ArrayList containing task objects that were previously saved.
     * @return ArrayList containing task objects.
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        List<String> loadData = Files.readAllLines(relativeFilePath);
        for (String taskString : loadData) {
            String[] taskDetails = taskString.split(" \\| ", 2);
            Task task = null;
            String taskType = taskDetails[0];
            switch (taskType) {
                case "T": {
                    task = parseStringToTask(taskDetails[1], "T");
                    break;
                }
                case "D": {
                    task = parseStringToTask(taskDetails[1], "D");
                    break;
                }
                case "E": {
                    task = parseStringToTask(taskDetails[1], "E");
                    break;
                }
                default:
                    break;
            }
            if (task != null) {
                taskList.add(task);
            }
        }
        return taskList;
    }

    /**
     * Saves tasks to hard disk.
     * @param taskList ArrayList containing tasks.
     * @throws IOException
     */
    public void save(ArrayList<Task> taskList) throws IOException {
        String data;
        String[] stringDataArr = new String[taskList.size()];
        int i = 0;
        for (Task task : taskList) {
            switch (task.getTaskType()) {
                case "T": {
                    data = "T" + " | " + task.getStatus() + " | " + task.getDescription();
                    stringDataArr[i] = data;
                    break;
                }
                case "D": {
                    data = "D" + " | " + task.getStatus() + " | " + task.getDescription();
                    stringDataArr[i] = data;
                    break;
                }
                case "E": {
                    data = "E" + " | " + task.getStatus() + " | " + task.getDescription();
                    stringDataArr[i] = data;
                    break;
                }
                default:
                    break;
            }
            i++;
        }
        Files.write(relativeFilePath, Arrays.asList(stringDataArr));
    }
}
