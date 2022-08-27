package duke;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.FileAlreadyExistsException;

/*
 * Handles saving and loading of tasks to and from a file.
 */
public class Storage {
    private String filePath;

    /**
     * Create a new Storage object to handle saving and loading of tasks.
     * 
     * @param filePath path to the file to save and load tasks from
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the file.
     * @return the tasks from the file
     * @throws DukeException if there is an error reading the file
     */
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

    /**
     * Saves the tasks to the file.
     * @param taskList the tasks to save
     * @throws DukeException if there is an error writing to the file
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            Files.createDirectories(Paths.get(filePath).getParent());
            Path saveLocation = Paths.get(filePath);
            try {
                Files.createFile(saveLocation);
            } catch (FileAlreadyExistsException ignored) {
            }
            Files.write(saveLocation, tasks.toSaveData().getBytes());
        } catch (IOException e) {
            throw new DukeException("IOException: " + e.toString());
        }
    }
}
