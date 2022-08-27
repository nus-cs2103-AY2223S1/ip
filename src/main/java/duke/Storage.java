package duke;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.FileAlreadyExistsException;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        Path saveLocation = Paths.get(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Files.lines(saveLocation).forEach((taskString) -> {
                String type = taskString.split(",")[0];
                switch (type) {
                case "T":
                    tasks.add(Task.fromSaveString(taskString));
                    break;
                case "E":
                    tasks.add(Event.fromSaveString(taskString));
                    break;
                case "D":
                    tasks.add(Deadline.fromSaveString(taskString));
                    break;
                default:
                    throw new DukeException("Tried to read unexpected save data.");
                }
            });
        } catch (IOException ignored) {
            // Save file does not exist, start afresh.
            return new ArrayList<>();
        }
        return tasks;
    }

    public void save(TaskList tasks) throws DukeException {
        try {
            saveTaskListToFile(tasks);
        } catch (IOException e) {
            throw new DukeException("IOException: " + e.toString());
        }
    }

    private static void saveTaskListToFile(TaskList tasks) throws IOException {
        Files.createDirectories(Paths.get("data"));
        Path saveLocation = Paths.get("data/tasks.txt");
        try {
            Files.createFile(saveLocation);
        } catch (FileAlreadyExistsException ignored) {
        }
        Files.write(saveLocation, tasks.toSaveData().getBytes());
    }
}
