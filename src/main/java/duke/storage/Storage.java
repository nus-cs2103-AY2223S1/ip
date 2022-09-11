package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.exceptions.BadDataException;
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
     * Constructs a storage with a directory path and a file path of the storage.
     *
     * @param directoryPath Directory path that the storage file is located in.
     * @param filePath      File path of the storage file.
     */
    public Storage(String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
    }

    private void createDirectoryIfNotExist() {
        Path directoryPath = Paths.get(this.directoryPath);
        File directory = new File(directoryPath.toUri());
        // noinspection ResultOfMethodCallIgnored because only making use of the side effect
        directory.mkdir();
    }

    private File createFileIfNotExist() throws DukeException {
        Path filePath = Paths.get(this.filePath);
        File file = new File(filePath.toUri());

        try {
            // noinspection ResultOfMethodCallIgnored because only making use of the side effect
            file.createNewFile();
        } catch (IOException e) {
            throw new BadDataException();
        }

        return file;
    }

    /**
     * Parses line into task.
     *
     * @param line Line in save file.
     * @return Parsed task.
     * @throws DukeException Exception thrown while parsing line.
     */
    private Task parseLine(String line) throws DukeException {
        String[] split = line.split(" \\| ");
        String tag = split[0];

        switch (tag) {
        case "T": {
            String done = split[1];
            String description = split[2];
            return Todo.create(done, description);
        }
        case "D": {
            String done = split[1];
            String description = split[2];
            String date = split[3];
            return Deadline.create(done, description, date);
        }
        case "E": {
            String done = split[1];
            String description = split[2];
            String date = split[3];
            return Event.create(done, description, date);
        }
        default:
            throw new BadDataException();
        }
    }

    /**
     * Loads tasks from file.
     *
     * @return The tasks loaded from the file.
     * @throws DukeException Exception that occurred during the loading of the tasks.
     */
    public TaskList load() throws DukeException {
        createDirectoryIfNotExist();
        File file = createFileIfNotExist();
        TaskList tasks = new TaskList();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = parseLine(line);
                tasks.addTask(task);
            }
        } catch (FileNotFoundException e) {
            // File creation failed, ignore and don't load tasks.
            return tasks;
        }

        return tasks;
    }

    /**
     * Saves tasks to file.
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
            assert task != null : "Task should not be null because i < tasks.size().";
            String line = task.getFileFormat();
            fw.write(line + "\n");
        }

        fw.close();
    }
}
