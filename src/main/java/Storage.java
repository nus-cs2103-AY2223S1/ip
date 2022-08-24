import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String HOME = System.getProperty("user.home");
    private static final String FILE_NAME = "duke.txt";
    private static final String DIR_NAME = "data";
    private static final Path FILE_PATH = Paths.get(HOME, DIR_NAME, FILE_NAME);
    private static final Path DIR_PATH = Paths.get(HOME, DIR_NAME);

    public Storage() {
        initialize();
    }

    private void initialize() throws DukeException {
        try {
            if (!isDirectoryPresent()) {
                Files.createDirectory(DIR_PATH);
            }
        } catch (IOException e) {
            throw new DukeException("Unable to create new data folder!");
        }

        try {
            if (!isFilePresent()) {
                Files.createFile(FILE_PATH);
            }
        } catch (IOException e) {
            throw new DukeException("Unable to create new data file!");
        }
    }

    private boolean isDirectoryPresent() {
        return Files.exists(DIR_PATH);
    }

    private boolean isFilePresent() {
        return Files.exists(FILE_PATH);
    }

    public ArrayList<Task> loadTasks() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            List<String> tasks = Files.readAllLines(FILE_PATH);
            for (String task : tasks) {
                Task decodedTask = decodeTaskFromString(task);
                taskList.add(decodedTask);
            }
        } catch (IOException e) {
            throw new DukeException("Unable to read from file!");
        }

        return taskList;
    }

    public void appendTaskToFile(Task task) throws DukeException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH.toString(), true);
            fw.write(encodeTaskToString(task));
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable write to file!");
        }
    }

    public void writeAllTasksToFile(List<Task> taskList) throws DukeException {
        try {
            List<String> list = new ArrayList<>();
            for (Task task : taskList) {
                list.add(encodeTaskToString(task));
            }
            Files.write(FILE_PATH, list);
        } catch (IOException e) {
            throw new DukeException("Unable write to file!");
        }
    }

    private Task decodeTaskFromString(String encodedTask) {
        String[] components = encodedTask.split(" \\| ", 3);
        Task task;
        switch (components[0]) {
        case "T":
            task = new Todo(components[2]);
            break;
        case "D":
            String[] deadlineInputs = components[2].split(" \\| ",2);
            task = new Deadline(deadlineInputs[0], deadlineInputs[1]);
            break;
        case "E":
            String[] eventInputs = components[2].split(" \\| ", 2);
            task = new Event(eventInputs[0], eventInputs[1]);
            break;
        default:
            task = null;
        }

        // "1" indicates task is done
        if (components[1].equals("1")) {
            task.markAsDone();
        }

        return task;
    }

    private String encodeTaskToString(Task task) {
        return task.encode();
    }
}
