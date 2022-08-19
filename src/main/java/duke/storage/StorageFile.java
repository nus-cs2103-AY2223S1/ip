package duke.storage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class StorageFile {

    public static final String DEFAULT_STORAGE_FILEPATH = "./data/duke.txt";
    private final Path path;

    public StorageFile(String filePath) {
        path = Paths.get(filePath);
    }

    /**
     * Saves the {@code TaskList} data to the storage file.
     *
     * @param taskList
     */
    public void save(TaskList taskList) throws DukeException {
        BufferedWriter writer;
        try {
            writer = Files.newBufferedWriter(path);
            writer.write("");
            writer.flush();
            writer.write(taskList.encodeToString());
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Error writing to file: " + path);
        }
    }

    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();

        try {
            List<String> contents = Files.readAllLines(path);

            String[] inputArray;
            String taskType, description;

            for (String line : contents) {
                inputArray = Arrays.stream(line.split("\\|")).map(String::trim).toArray(String[]::new);
                taskType = inputArray[0];
                description = inputArray[2];
                Task task = null;

                switch (taskType) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    task = new Deadline(description, inputArray[3]);
                    break;
                case "E":
                    task = new Event(description, inputArray[3]);
                    break;
                }

                if (task != null) {
                    if (inputArray[1].equals("1")) {
                        task.mark();
                    } else {
                        task.unmark();
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
