package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles writing the task list to a file, and encoding and decoding.
 * Storage format of a Task is type|marked|description(|time) where
 * type is the type of Task (T: Todo; D: Deadline; E: Event)
 * marked is 1 if the task is marked as completed and 0 otherwise
 * description is the task description
 * time is the time for deadlines and events
 * Tasks are separated by newlines in the written file
 */
public class Storage {
    public final String DEFAULT_STORAGE_FILEPATH = "./data/duke.txt";

    /**
     * Creates the storage file at /data/duke.txt if it does not already exist
     * @throws IOException if there is a problem creating the directory and/or file
     */
    public void createStorage() throws IOException {
        if (!Files.exists(Path.of("./data/")) || !Files.isDirectory(Path.of("./data"))) {
            Files.createDirectory(Path.of("./data"));
        }
        if (!Files.exists(Path.of(DEFAULT_STORAGE_FILEPATH))) {
            Files.createFile(Path.of(DEFAULT_STORAGE_FILEPATH));
        }
    }

    /**
     * Loads a TaskList from storage
     * @return the loaded TaskList
     * @throws IOException if there is an error reading the storage file
     */
    public TaskList loadFromStorage() throws IOException {
        List<String> contents = Files.readAllLines(Path.of(DEFAULT_STORAGE_FILEPATH));
        return decode(contents);
    }

    /**
     * Writes a TaskList to storage
     * @param t the TaskList to write
     * @throws IOException if there is an error writing to the storage file
     */
    public void writeToStorage(TaskList t) throws IOException {
        Files.write(Path.of(DEFAULT_STORAGE_FILEPATH), encode(t));
    }

    private List<String> encode(TaskList t) {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < t.getSize(); i++) {
            data.add(t.get(i).getStorageString());
        }
        return data;
    }

    private TaskList decode(List<String> data) {
        TaskList t = new TaskList();
        for (int i = 0; i < data.size(); i++) {
            String[] args = data.get(i).split("\\|");
            switch (args[0]) {
            case "T":
                t.add(new Todo(args[2]));
                if (args[1].equals("1")) {
                    t.mark(i);
                }
                break;
            case "D":
                t.add(new Deadline(args[2], args[3]));
                if (args[1].equals("1")) {
                    t.mark(i);
                }
                break;
            case "E":
                t.add(new Event(args[2], args[3]));
                if (args[1].equals("1")) {
                    t.mark(i);
                }
                break;
            }
        }
        return t;
    }
}
