import JennyTasks.DeadlineJennyTask;
import JennyTasks.EventJennyTask;
import JennyTasks.JennyTask;
import JennyTasks.TodoJennyTask;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles file I/O operations of the JennyBot application.
 * CS2103 Week 3
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class JennyStorage {
    private final Path storageLocation;

    /**
     * Creates an instance of the class {@code JennyStorage} to handle file I/O operations of the JennyBot application.
     *
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
            JennyPrinter.echo(String.format("JennyStorage: JennyTask storage initialised at: [%s]", this.storageLocation));
        } else {
            JennyPrinter.echo(String.format("JennyStorage: JennyTask storage already exists at: [%s]", this.storageLocation));
        }
    }

    /**
     * Saves a list of jennyTasks in the file {@code jennyTasks.txt} at the default save location
     * {@code %HOME%\.jenny\storage\jennyTasks}
     *
     * @param jennyTasks The list of jennyTasks to be saved.
     * @throws IOException When file I/O operation fails.
     */
    public void save(ArrayList<JennyTask> jennyTasks) throws IOException {
        String fileLocation = String.format("%s\\jennyTasks.txt", this.storageLocation.toString());
        FileWriter fileWriter = new FileWriter(fileLocation);
        for (JennyTask jennyTask : jennyTasks) {
            fileWriter.write(String.format("%s\n", jennyTask.save()));
        }
        fileWriter.close();
        JennyPrinter.echo(String.format("JennyStorage: File written successfully in [%s].", this.storageLocation));
    }

    /**
     * Loads a list of tasks in the file {@code taskItems.txt} at the default save location
     * {@code %HOME%\.jenny\storage\tasks}
     *
     * @return A list of tasks that was loaded.
     * @throws IOException When file I/O operation fails.
     */
    public ArrayList<JennyTask> load() throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(new FileReader(String.format("%s\\jennyTasks.txt", this.storageLocation.toString())));
        ArrayList<JennyTask> jennyTasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String jennyTask = scanner.nextLine();
            String[] data = jennyTask.split(",");
            switch (data[0]) {
                case "DeadlineJennyTask":
                    jennyTasks.add(new DeadlineJennyTask(data[1], data[2]));
                    break;
                case "EventJennyTask":
                    jennyTasks.add(new EventJennyTask(data[1], data[2]));
                    break;
                case "TodoJennyTask":
                    jennyTasks.add(new TodoJennyTask(data[1]));
                    break;
                default:
                    JennyPrinter.echo("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        return jennyTasks;
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
