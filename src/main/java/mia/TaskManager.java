package mia;

import general.utils.StoredFile;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A {@code TaskManager} contains a list of tasks that the user can add, edit, view, and delete.
 *
 * @author Richard Dominick
 */
public class TaskManager {
    private final List<Task> tasks;
    private final StoredFile storedFile;

    /**
     * Creates a new {@code TaskManager} instance from the state present in the specified save file.
     * If the file does not exist, a default state is used. If the file has an invalid format, as much of
     * the parseable state will be used, while everything else will be dropped.
     *
     * @param saveFile The path to the file containing the saved state information
     */
    public TaskManager(String saveFile) {
        List<Task> tasks;
        storedFile = StoredFile.from(saveFile);
        if (storedFile.fileExists()) {
            try {
                tasks = storedFile.getTextContent().lines().map(String::strip).flatMap(
                        line -> {
                            if (!line.equals("")) {
                                switch (line.charAt(0)) {
                                case 'T':
                                    return Stream.of(Todo.fromSaveFormat(line.substring(3)));
                                case 'E':
                                    return Stream.of(Event.fromSaveFormat(line.substring(3)));
                                case 'D':
                                    return Stream.of(Deadline.fromSaveFormat(line.substring(3)));
                                default:
                                    System.out.printf("Invalid save file format: %s\nSkipping this line... please check for corrupted data.\n", line);
                                    return Stream.empty();
                                }
                            }
                            return Stream.empty();
                        }
                ).collect(Collectors.toList());
            } catch (FileNotFoundException e) {
                System.out.println("Something went wrong while opening the file. Defaulting to empty initial state.");
                tasks = new ArrayList<>();
            }
        } else {
            System.out.println("Save file not found, starting from scratch...");
            tasks = new ArrayList<>();
        }
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the list of tasks to be executed, and saves the updated save file to disk.
     *
     * @param task The task to be added
     */
    public void addTask(Task task) {
        try {
            tasks.add(task);
        } finally {
            saveToDisk();
        }
    }

    /**
     * Deletes a task, and saves the updated save file to disk.
     *
     * @param number the task number
     * @return {@code true} if the task was deleted, {@code false} otherwise
     */
    public boolean deleteTask(int number) {
        try {
            if (number > tasks.size()) {
                return false;
            }
            tasks.remove(number - 1);
            return true;
        } finally {
            saveToDisk();
        }
    }

    /**
     * Marks a task as completed, and saves the updated save file to disk.
     *
     * @param number the task number
     * @return {@code true} if the task is modified, {@code false} otherwise
     */
    public boolean checkTask(int number) {
        try {
            if (number > tasks.size()) {
                return false;
            }
            return tasks.get(number - 1).setCompleted(true);
        } finally {
            saveToDisk();
        }
    }

    /**
     * Marks a task as incomplete, and saves the updated save file to disk.
     *
     * @param number the task number
     * @return {@code true} if the task is modified, {@code false} otherwise
     */
    public boolean uncheckTask(int number) {
        try {
            if (number > tasks.size()) {
                return false;
            }
            return tasks.get(number - 1).setCompleted(false);
        } finally {
            saveToDisk();
        }
    }

    /**
     * Searches all tasks for titles matching the given query.
     *
     * @param query The query to be word-matched against the task titles
     * @return A {@code String} representation of the tasks that matched the given query
     */
    public String search(String query) {
        final List<Task> matches = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            final Task t = tasks.get(i);
            if (t.matches(query)) {
                matches.add(t);
            }
        }
        final StringBuilder sb = new StringBuilder("Matching Tasks:\n");
        return getStringFromTasksList(matches, sb);
    }

    private void saveToDisk() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(tasks.get(i).toSaveFormat());
            sb.append("\n");
        }
        storedFile.writeText(sb.toString());
    }

    private static String getStringFromTasksList(List<Task> matches, StringBuilder sb) {
        for (int i = 0; i < matches.size(); i++) {
            sb.append(i + 1).append(". ").append(matches.get(i).toString());
            if (i != matches.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Your Tasks:\n");
        return getStringFromTasksList(tasks, sb);
    }
}
