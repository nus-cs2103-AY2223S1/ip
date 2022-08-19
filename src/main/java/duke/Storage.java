package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Storage {
    private final Path path;

    public Storage(Path path) {
        this.path = path;
    }

    /**
     * Returns a new TaskList from a file of serialized tasks.
     *
     * @return the new TaskList
     * @throws IOException if an I/O error occurs reading the file
     * @since Level-7
     */
    public TaskList load() throws IOException {
        List<Task> tasks = Files.readAllLines(path)
                .stream()
                .map(Task::deserialize)
                .collect(Collectors.toList());
        return new TaskList(tasks);
    }

    /**
     * Writes the list of tasks to a file at the given path.
     * If the file already exists, it will be overwritten.
     *
     * @throws IOException if an I/O error occurs writing to the file
     * @since Level-7
     */
    public void save(TaskList tasks) throws IOException {
        if (Files.notExists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        String serialized = tasks.stream()
                .map(Task::serialize)
                .map(str -> str + "\n")
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        Files.writeString(path, serialized);
    }
}