package tako;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import tako.task.Deadline;
import tako.task.Event;
import tako.task.Task;
import tako.task.Todo;

/**
 * Stores and loads data related to tasks.
 */
public class Storage {
    private Path tasksPath;

    /**
     * Constructor for Storage with the path to store tasks at.
     *
     * @param tasksPath Path to store tasks at.
     */
    public Storage(String tasksPath) {
        this.tasksPath = Paths.get(tasksPath);
    }

    /**
     * Saves the task to a file.
     *
     * @param task Task to save.
     * @throws IOException If the file does not exist.
     */
    public void saveToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(tasksPath.toString(), true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(task.convertToFileFormat());
        bw.newLine();
        bw.close();
    }

    /**
     * Saves all tasks in a task list to a file.
     *
     * @param tasks Tasks to save.
     * @throws IOException If the file does not exist.
     */
    public void saveToFile(TaskList tasks) throws IOException {
        BufferedWriter bw = Files.newBufferedWriter(tasksPath);
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.get(i);
            bw.write(task.convertToFileFormat());
            bw.newLine();
        }
        bw.close();
    }

    private Task convertToTask(String line) {
        String[] splitLine = line.split(" \\| ");
        String taskType = splitLine[0];
        String description = splitLine[2];
        Task task = null;
        switch (taskType) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            LocalDateTime deadlineDateTime = LocalDateTime.parse(splitLine[3]);
            task = new Deadline(description, deadlineDateTime);
            break;
        case "E":
            LocalDateTime eventDateTime = LocalDateTime.parse(splitLine[3]);
            task = new Event(description, eventDateTime);
            break;
        default:
            break;
        }
        if (task != null) {
            String isTaskDoneString = splitLine[1];
            String done = "1";
            if (isTaskDoneString.equals(done)) {
                task.markAsDone();
            }
        }
        return task;
    }

    /**
     * Loads tasks from the tasks' path into a list.
     * If the file at the tasks' path does not exist, a new file is created instead.
     *
     * @return A list of saved tasks.
     * @throws IOException If file or directory creation is unsuccessful.
     *                     If an error occurs while reading the file.
     */
    public List<Task> load() throws IOException {
        createSaveFile();
        List<Task> tasks = new ArrayList<>();
        BufferedReader br = Files.newBufferedReader(tasksPath);
        String line = br.readLine();
        while (line != null) {
            tasks.add(convertToTask(line));
            line = br.readLine();
        }
        br.close();
        return tasks;
    }

    private void createSaveFile() throws IOException{
        Path parent = tasksPath.getParent();
        if (!Files.isDirectory(parent)) {
            Files.createDirectories(parent);
        }
        if (!Files.isRegularFile(tasksPath)) {
            Files.createFile(tasksPath);
        }
    }
}
