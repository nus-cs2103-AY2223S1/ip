package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Optional;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Storage class that handles the storage and retrieval of tasks.
 */
public class Storage {
    private final String tasksFolderPath;
    private String fileName;

    /**
     * Constructs a new Storage in the application folder given a filename.
     *
     * @param fileName The name of the file to store tasks in.
     * @throws DukeException Exception thrown if the file cannot be found.
     */
    public Storage(String fileName) throws DukeException {
        String tasksFolderPath = null;
        try {
            String classPath = Path.of(this.getClass()
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation().toURI()).toString();
            String classDirectoryPath = classPath.substring(0, classPath.lastIndexOf(File.separator));
            tasksFolderPath = URLDecoder.decode(classDirectoryPath, StandardCharsets.UTF_8)
                    .concat(File.separator + "data");
            File tasksFolder = new File(tasksFolderPath);
            if (!tasksFolder.exists() && !tasksFolder.mkdir()) {
                throw new DukeException("Unable to save tasks to disk.");
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        this.tasksFolderPath = tasksFolderPath;
        this.fileName = fileName;
    }

    public Optional<TaskList> loadTasks() {
        return loadTasks(this.fileName);
    }

    /**
     * Loads the list of task from a previously save text file.
     *
     * @param fileName Name of the file.
     * @return optional array list of tasks if no errors are found.
     */
    public Optional<TaskList> loadTasks(String fileName) {
        this.fileName = fileName;
        TaskList tasks = new TaskList();

        File file = new File(tasksFolderPath.concat(File.separator + fileName));
        if (!file.exists()) {
            return Optional.empty();
        }
        try {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            br.lines().forEach(line -> {
                String[] entries = line.strip().split("\\|", 3);
                Task.Type type = Task.Type.decode(entries[0]);
                boolean isCompleted = entries[1].equals("1");
                switch (type) {
                case DEADLINE:
                    tasks.tryAddTask(Deadline.tryDecode(entries[2], isCompleted).orElse(null));
                    break;
                case EVENT:
                    tasks.tryAddTask(Event.decode(entries[2], isCompleted));
                    break;
                case TODO:
                    tasks.tryAddTask(ToDo.decode(entries[2], isCompleted));
                    break;
                default:
                    break;
                }
            });
            br.close();
            return Optional.of(tasks);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private void writeToFile(String fileName, String content) throws DukeException {
        String filePath = tasksFolderPath.concat(File.separator + fileName);
        File file = new File(filePath);
        try {
            FileWriter writer = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            throw new DukeException("Could not save file! " + e.getMessage());
        }
    }

    /**
     * Encodes and saves the given list of tasks to a file.
     *
     * @param tasks the list of tasks to save.
     * @return true if the tasks were saved successfully.
     */
    public boolean saveTasks(TaskList tasks) {
        try {
            StringBuilder sb = new StringBuilder();
            for (Task task : tasks) {
                sb.append(task.encode()).append(System.lineSeparator());
            }
            this.writeToFile(fileName, sb.toString());
            return true;
        } catch (DukeException e) {
            return false;
        }
    }
}
