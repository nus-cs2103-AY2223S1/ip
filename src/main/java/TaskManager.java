import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskManager {
    private final List<Task> tasks;

    public TaskManager(String saveFile) {
        List<Task> tasks;
        StoredFile storedFile = StoredFile.from(saveFile);
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

    public void addTask(Task task) {
        try {
            tasks.add(task);
        } finally {
            saveToDisk();
        }
    }

    /**
     * Deletes a task.
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
     * Marks a task as completed.
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
     * Marks a task as incomplete.
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

    private void saveToDisk() {
        final String dataDirectory = "data";
        final String dataPath = "data/Mia.txt";
        if (tasks.size() > 0) {
            try {
                File directory = new File(dataDirectory);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                File file = new File(dataPath);
                file.createNewFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataPath))) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < tasks.size(); i++) {
                        sb.append(tasks.get(i).toSaveFormat());
                        sb.append("\n");
                    }
                    writer.write(sb.toString()); // do something with the file we've opened
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Your Tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i).toString());
            if (i != tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
