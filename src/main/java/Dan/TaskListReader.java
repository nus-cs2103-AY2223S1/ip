package dan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskListReader {
    /* Stores data separated by <SEPARATOR> in the format with no whitespace:
    eg. SEPARATOR = "|"
    Tasktype{T,D,E}|Task.isDone{1,0}|Task.Name|(optional)Deadline String
     */
    public static final String SEPARATOR = "%&";
    private static final String ROOT_PATH = "src/main/data/";
    private final Path filePath;

    public TaskListReader(String fileName) {
        this.filePath = Paths.get(ROOT_PATH + fileName);
    }

    public String getPath() {
        return this.filePath.toString();
    }

    public void createFile() throws IOException {
        Files.createDirectories(this.filePath.getParent());
        Files.createFile(this.filePath);
    }

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

    public void writeTaskListToFile(TaskList taskList) throws IOException {
        Files.write(filePath, taskList.getTasks().stream()
                .map(x -> x.toDataString(SEPARATOR))
                .collect(Collectors.toList())
        );
    }

}
