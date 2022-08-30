package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private Path dir;
    private Path file;

    public Storage(String dirName, String fileName) {
        this.dir = Paths.get(dirName);
        this.file = Paths.get(dirName, fileName);
    }

    public void initializeDir() throws IOException {
        if (!Files.exists(dir)) {
            Files.createDirectory(dir);
        }
    }

    public void initializeFile() throws IOException {
        if (!Files.exists(file)) {
            Files.createFile(file);
        }
    }

    private static void addTaskToArrayList(String t, ArrayList<Task> acc) {
        String[] entries = t.split("\\|");
        switch (entries[0]) {
        case "T":
            if (entries[1].equals("1")) {
                acc.add(new Todo(entries[2], true));
            } else {
                acc.add(new Todo(entries[2], false));
            }
            break;

        case "D":
            if (entries[1].equals("1")) {
                acc.add(new Deadline(entries[2], entries[3], true));
            } else {
                acc.add(new Deadline(entries[2], entries[3], false));
            }
            break;

        case "E":
            if (entries[1].equals("1")) {
                acc.add(new Event(entries[2], entries[3], true));
            } else {
                acc.add(new Event(entries[2], entries[3], false));
            }
            break;

        default:
            throw new IndexOutOfBoundsException();
        }
    }

    public ArrayList<Task> readFile() throws IOException {
        ArrayList<Task> result = new ArrayList<>();
        List<String> previousTasks = Files.readAllLines(file);
        try {
            previousTasks.forEach(t -> addTaskToArrayList(t, result));
        } catch (IndexOutOfBoundsException e) {
            throw new IOException();
        }
        return result;
    }

    public void updateFile(TaskList ts) throws IOException {
        ArrayList<String> taskDescriptions = new ArrayList<>();
        ts.getAllTasks().forEach(t -> taskDescriptions.add(t.toSaveData()));
        Files.write(file, taskDescriptions);
    }
}
