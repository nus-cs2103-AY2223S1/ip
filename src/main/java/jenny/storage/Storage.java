package jenny.storage;

import jenny.util.Printer;
import jenny.tasks.AbstractTask;
import jenny.tasks.Deadline;
import jenny.tasks.Event;
import jenny.tasks.Todo;

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
 * Handles file I/O operations of the jenny.JennyBot application.
 * CS2103 Week 3
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class Storage {
    private final Path storageLocation;

    /**
     * Creates an instance of the class {@code jenny.storage.Storage} to handle file I/O operations of the jenny.JennyBot application.
     *
     * @throws IOException When file I/O operation fails.
     */
    public Storage() throws IOException {
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
            Printer.echo(String.format("jenny.storage.Storage: AbstractTask storage initialised at: [%s]", this.storageLocation));
        } else {
            Printer.echo(String.format("jenny.storage.Storage: AbstractTask storage already exists at: [%s]", this.storageLocation));
        }
    }

    /**
     * Saves a list of abstractTasks in the file {@code abstractTasks.txt} at the default save location
     * {@code %HOME%\.jenny\storage\abstractTasks}
     *
     * @param abstractTasks The list of abstractTasks to be saved.
     * @throws IOException When file I/O operation fails.
     */
    public void save(ArrayList<AbstractTask> abstractTasks) throws IOException {
        String fileLocation = String.format("%s\\abstractTasks.txt", this.storageLocation.toString());
        FileWriter fileWriter = new FileWriter(fileLocation);
        for (AbstractTask abstractTask : abstractTasks) {
            fileWriter.write(String.format("%s\n", abstractTask.save()));
        }
        fileWriter.close();
        Printer.echo(String.format("jenny.storage.Storage: File written successfully in [%s].", this.storageLocation));
    }

    /**
     * Loads a list of tasks in the file {@code taskItems.txt} at the default save location
     * {@code %HOME%\.jenny\storage\tasks}
     *
     * @return A list of tasks that was loaded.
     * @throws IOException When file I/O operation fails.
     */
    public ArrayList<AbstractTask> load() throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(new FileReader(String.format("%s\\abstractTasks.txt", this.storageLocation.toString())));
        ArrayList<AbstractTask> abstractTasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String jennyTask = scanner.nextLine();
            String[] data = jennyTask.split(",");
            switch (data[0]) {
                case "Deadline":
                    abstractTasks.add(new Deadline(data[1], data[2]));
                    break;
                case "Event":
                    abstractTasks.add(new Event(data[1], data[2]));
                    break;
                case "Todo":
                    abstractTasks.add(new Todo(data[1]));
                    break;
                default:
                    Printer.echo("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        return abstractTasks;
    }

    /**
     * {@inheritDoc}
     * Returns the path used by jenny.storage.Storage.
     */
    @Override
    public String toString() {
        return String.format("jenny.storage.Storage: [%s]", this.storageLocation);
    }
}
