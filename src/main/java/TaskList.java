import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;

public class TaskList {
    private ArrayList<Task> taskList;
    private static final Path DIRECTORY_PATH = Paths.get("data");
    private static final Path FILE_PATH = Paths.get("data/gibson.txt");

    public TaskList() {
        taskList = new ArrayList<Task>();
        try {
            if (Files.exists(DIRECTORY_PATH)) {
                if (Files.exists(FILE_PATH)) {
                    load();
                } else {
                    Files.createFile(FILE_PATH);
                }
            } else {
                Files.createDirectories(DIRECTORY_PATH);
                Files.createFile(FILE_PATH);
            }
        } catch (IOException e) {
            System.err.println("Failed to create directory/file. " + e.getMessage());
        }
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public void add(Task task) {
        taskList.add(task);
        save();
    }

    public void remove(int i) {
        taskList.remove(i);
        save();
    }

    public int size() {
        return taskList.size();
    }
    private void save() {
        try {
            Files.write(FILE_PATH, toString().getBytes());
        } catch (IOException e) {
            System.err.println("Failed to write to Gibson.txt. " + e.getMessage());
        }
    }

    private void load() {
        // TODO
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(i + 1 + "." + taskList.get(i).toString());
            if (i + 1 < taskList.size()) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
