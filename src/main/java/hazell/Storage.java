package hazell;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private Path path;

    /**
     * Returns a Storage object pointing to the file used to store Hazell data locally.
     *
     * Before doing so, it ensures that the folder exists.
     * @throws IOException
     */
    public Storage(String filePath) throws IOException {
        Path path = Path.of(filePath);
        Path containingPath = path.getParent();
        if (!Files.exists(containingPath)) {
            Files.createDirectories(containingPath);
        }
        String currentDirString = System.getProperty("user.dir");
        Path currentDir = Paths.get(currentDirString);
        this.path = currentDir.resolve(path);
    }

    /**
     * Load from local storage.
     * @return List of tasks, unserialised
     * @throws IOException
     */
    public List<Task> load() throws IOException {
        Scanner sc = new Scanner(this.path);
        List<Task> tasks = new ArrayList<>();
        while (sc.hasNextLine()) {
            Task task = Task.unserialise(sc.nextLine().strip());
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Store tasks to local storage.
     * @param tasks Lists of tasks to be serialised
     * @throws IOException
     */
    public void store(List<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(this.path.toFile(), false);
        for (Task task : tasks) {
            writer.write(task.serialise());
            writer.write("\n");
        }
        writer.close();
    }
}
