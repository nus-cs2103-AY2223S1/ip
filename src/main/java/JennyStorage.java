import TaskItems.TaskItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles file I/O operations of the Jenny application.
 * CS2103 Week 3
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class JennyStorage {
    private final Path storageLocation;

    /**
     * Creates an instance of the class {@code JennyStorage} to handle file I/O operations of the Jenny application.
     * @throws IOException When file I/O operation fails.
     */
    public JennyStorage() throws IOException {
        String userHome = System.getProperty("user.home");
        this.storageLocation = Paths.get(userHome, ".jenny", "storage", "tasks");
        boolean directoryExists = Files.exists(this.storageLocation);
        if (!directoryExists) {
            Files.createDirectories(storageLocation);
        }
        String storageLocation = this.storageLocation.toString();
        File taskStorage = new File(storageLocation);
        boolean fileCreated = taskStorage.createNewFile();
        if (fileCreated) {
            JennyPrinter.echo(String.format("JennyStorage: Task storage initialised at: [%s]",
                    this.storageLocation));
        } else {
            JennyPrinter.echo(String.format("JennyStorage: Task storage already exists at: [%s]",
                    this.storageLocation));
        }
    }

    /**
     * Saves a list of tasks in the file {@code taskItems.txt} at the default save location
     * {@code %HOME%\.jenny\storage\tasks}
     * @param taskItems The list of tasks to be saved.
     * @throws IOException When file I/O operation fails.
     */
    public void save(ArrayList<TaskItem> taskItems) throws IOException {
        FileWriter fileWriter = new FileWriter("taskItems.txt");
        for (TaskItem taskItem : taskItems) {
            fileWriter.write(String.format("%s\n", taskItem.toString()));
        }
        fileWriter.close();
        JennyPrinter.echo(String.format("JennyStorage: File written successfully in [%s].",
                this.storageLocation.toString()));
    }

    /**
     * Loads a list of tasks in the file {@code taskItems.txt} at the default save location
     * {@code %HOME%\.jenny\storage\tasks}
     * @return A list of tasks that was loaded.
     * @throws IOException When file I/O operation fails.
     */
    public ArrayList<TaskItem> load() throws IOException {
        Scanner scanner = new Scanner(this.storageLocation);
        ArrayList<TaskItem> taskItems = new ArrayList<>();
        // TODO: Implement file loading.
        return taskItems;
    }

    /**
     * {@inheritDoc}
     * Returns the path used by JennyStorage.
     */
    @Override
    public String toString() {
        return String.format("JennyStorage: [%s]", this.storageLocation);
    }
}
