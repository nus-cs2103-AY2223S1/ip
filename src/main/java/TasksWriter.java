import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TasksWriter {
    private ArrayList<Task> tasks;
    private String userDirectory = System.getProperty("user.dir");

    TasksWriter(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private String generateTasksToAdd() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            stringBuilder.append(task.toString() + System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    private String relativePath() {
        Path path = Paths.get(userDirectory, "data", "duke.txt");
        return path.toAbsolutePath().toString();
    }

    public void writeToFile() throws DukeException {
        try {
            FileWriter fw = new FileWriter(relativePath());
            fw.write(generateTasksToAdd());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Something went wrong " + e.toString());
        }
    }
}
