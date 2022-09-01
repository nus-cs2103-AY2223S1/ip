package dan;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Handles the processing of data for the list of tasks with the system.
 * Namely, the storage, loading and saving of data.
 */
public class TaskListReader {
    /* Stores data separated by <SEPARATOR> in the format with no whitespace:
    eg. SEPARATOR = "|"
    Tasktype{T,D,E}|Task.isDone{1,0}|Task.Name|(optional)Deadline String
     */
    private static final String SEPARATOR = "%&";
    private static final String ROOT_PATH = "src/main/data/";
    private final Path filePath;

    /**
     * Constructor method.
     *
     * @param fileName the file name specified
     */
    public TaskListReader(String fileName) {
        this.filePath = Paths.get(ROOT_PATH + fileName);
    }

    /**
     * Getter for the full path of the data file.
     *
     * @return file path of the data file its loading and saving from.
     */
    public String getPath() {
        return this.filePath.toString();
    }

    /**
     * Creates the data file if it doesn't exist.
     *
     * @throws IOException If there are any I/O issues
     */
    public void createFile() throws IOException {
        Files.createDirectories(this.filePath.getParent());
        Files.createFile(this.filePath);
    }

    /**
     * Loads the data file, and processes it into a List of Tasks to prepare for it to be converted to a TaskList.
     *
     * @return List of Tasks created from the specified data file
     * @throws IOException If there are any I/O issues.
     */
    public List<Task> readTaskListFromFile() throws IOException {
        List<String> lines = Files.readAllLines(this.filePath);
        List<Task> taskList = new ArrayList<>(lines.size());
        for (String line : lines) {
            String[] data = line.split(SEPARATOR);
            switch (data[0].strip()) {
            case "T":
                taskList.add(new ToDo(data[2].strip()));
                break;

            case "E":
                taskList.add(new Event(data[2].strip(), data[3].strip()));
                break;

            case "D":
                taskList.add(new Deadline(data[2].strip(), data[3].strip()));
                break;

            default:
                break;
            }

            Task addedTask = taskList.get(taskList.size() - 1);
            if (data[1].strip().equals("1")) {
                addedTask.setDone(true);
            }
        }
        return taskList;
    }

    /**
     * Writes the current list of tasks into the data file
     *
     * @param taskList The list of tasks
     * @throws IOException If there are any I/O issues
     */
    public void writeTaskListToFile(TaskList taskList) throws IOException {
        Files.write(filePath, taskList.getTasks().stream()
                .map(x -> x.toDataString(SEPARATOR))
                .collect(Collectors.toList())
        );
    }

}
