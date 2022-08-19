import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String directoryPath;
    private final String filePath;

    public Storage(String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
    }

    /**
     * Load tasks from file.
     *
     * @return The tasks loaded from the file.
     */
    public ArrayList<Task> load() throws DukeException {
        Path directoryPath = Paths.get(this.directoryPath);
        Path filePath = Paths.get(this.filePath);
        File directory = new File(directoryPath.toUri());
        // Create directory if it does not exist.
        // noinspection ResultOfMethodCallIgnored because not only making use of the side effect
        directory.mkdir();
        File file = new File(filePath.toUri());

        ArrayList<Task> tasks = new ArrayList<>();
        try {
            // Create file if it does not exist.
            if (file.createNewFile()) {
                // If file did not exist before this, there are no tasks.
                return tasks;
            }
        } catch (IOException e) {
            throw DukeException.badData;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] split = line.split(" \\| ");
                Task task;
                switch (split[0]) {
                    case "T":
                        task = Todo.create(split[1], split[2]);
                        break;
                    case "D":
                        task = Deadline.create(split[1], split[2], split[3]);
                        break;
                    case "E":
                        task = Event.create(split[1], split[2], split[3]);
                        break;
                    default:
                        throw DukeException.badData;
                }

                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            // Should not happen because file is created beforehand.
            return tasks;
        }

        return tasks;
    }

    /**
     * Save tasks to file.
     *
     * @param tasks The tasks to be saved to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        Path filePath = Paths.get(this.filePath);
        File file = new File(filePath.toUri());
        FileWriter fw = new FileWriter(file);

        for (Task task : tasks) {
            String line = task.getFileFormat();
            fw.write(line + "\n");
        }

        fw.close();
    }
}
