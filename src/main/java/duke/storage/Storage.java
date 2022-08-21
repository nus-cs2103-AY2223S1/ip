package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents a storage in the Duke application.
 * Responsible for loading and saving tasks to the file system.
 */
public class Storage {
    private final String directoryPath;
    private final String filePath;

    /**
     * Constructor for a storage that takes in directory path and file path of the storage.
     *
     * @param directoryPath Directory path that the storage file is located in.
     * @param filePath      File path of the storage file.
     */
    public Storage(String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
    }

    /**
     * Load tasks from file.
     *
     * @return The tasks loaded from the file.
     * @throws DukeException Exception that occurred during the loading of the tasks.
     */
    public TaskList load() throws DukeException {
        Path directoryPath = Paths.get(this.directoryPath);
        Path filePath = Paths.get(this.filePath);
        File directory = new File(directoryPath.toUri());
        // Create directory if it does not exist.
        // noinspection ResultOfMethodCallIgnored because not only making use of the side effect
        directory.mkdir();
        File file = new File(filePath.toUri());

        TaskList tasks = new TaskList();
        try {
            // Create file if it does not exist.
            if (file.createNewFile()) {
                // If file did not exist before this, there are no tasks.
                return tasks;
            }
        } catch (IOException e) {
            throw DukeException.BAD_DATA;
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
                    throw DukeException.BAD_DATA;
                }

                tasks.addTask(task);
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
     * @param tasks        Tasks to be saved to the file.
     * @throws IOException Exception that occurred during the saving of the tasks.
     */
    public void save(TaskList tasks) throws IOException {
        Path filePath = Paths.get(this.filePath);
        File file = new File(filePath.toUri());
        FileWriter fw = new FileWriter(file);

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            String line = task.getFileFormat();
            fw.write(line + "\n");
        }

        fw.close();
    }
}
