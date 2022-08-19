package duke.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A TaskList stores a list of tasks.
 */
public class TaskList {
    private final List<Task> list;

    private TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Returns a new TaskList with no tasks.
     * Use this static factory method instead of the constructor.
     *
     * @return a new TaskList with no tasks
     * @since Level-7
     */
    public static TaskList newEmptyTaskList() {
        return new TaskList(new ArrayList<>());
    }

    /**
     * Returns a new TaskList from a file of serialized tasks.
     *
     * @param path the path to the file of tasks
     * @return the new TaskList
     * @throws IOException if an I/O error occurs reading the file
     * @since Level-7
     */
    public static TaskList readFromFile(Path path) throws IOException {
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
     * @param path the path to write the tasks to
     * @throws IOException if an I/O error occurs writing to the file
     * @since Level-7
     */
    public void writeToFile(Path path) throws IOException {
        if (Files.notExists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        String serialized = list.stream()
                .map(Task::serialize)
                .map(str -> str + "\n")
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        Files.writeString(path, serialized);
    }


    /**
     * Adds a task to the list.
     *
     * @param task a string value of the task to add
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Deletes the task at the given index from the list.
     *
     * @param index the index of the task to delete
     */
    public void deleteTask(int index) {
        list.remove(index);
    }

    /**
     * Returns the task at the given index.
     *
     * @param index the index of the task to return
     * @return the task at the given index
     */
    public Task getTask(int index) {
        return list.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks in the list
     */
    public int size() {
        return list.size();
    }

    /**
     * Enumerates the tasks stored in this TaskList.
     *
     * @return the enumerated list of tasks
     */
    @Override
    public String toString() {
        return IntStream.range(0, list.size())
                .mapToObj(i -> String.format("%d. %s\n", i + 1, list.get(i)))
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString()
                .trim();
    }
}
