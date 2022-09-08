import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private Path taskListPath = null;

    public Storage(String fileName) {
        this.taskListPath = Paths.get(".", fileName);
    }

    public ArrayList<Task> loadTaskList() {
        // Check if file exist
        ArrayList<Task> tasks = new ArrayList<>();
        if (Files.exists(taskListPath)) {
            try {
                List<String> taskList = Files.readAllLines(taskListPath);
                for (int i = 0; i < taskList.size(); i++) {
                    String taskDetail = taskList.get(i);

                }

            } catch (IOException | SecurityException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                Files.createFile(taskListPath);
            } catch (IOException | SecurityException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

