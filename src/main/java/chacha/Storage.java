package chacha;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.BufferedReader;
import chacha.tasks.Deadline;
import chacha.tasks.Event;
import chacha.tasks.Task;
import chacha.tasks.Todo;

/**
 * Storage class to handle saving and loading data.
 */
public class Storage {
    private Path path;

    public Storage(String path) {
        this.path = Paths.get(path);
    }

    
    /** 
     * Saves task list of tasks to a file.
     * 
     * @param tasks Tasks to save.
     * @throws IOException If the file does not exist.
     */
    public void saveToFile(TaskList tasks) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(path);
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.get(i);
            writer.write(taskToText(task));
            writer.newLine();
        }
        writer.close();
    }

    /** 
     * Loads tasks from file into task list of tasks.
     * Creates new file if path does not exist.
     * 
     * @return List of tasks.
     * @throws IOException If error when reading file.
     */
    public ArrayList<Task> load() throws IOException {
        Path parent = path.getParent();
        if (!Files.isDirectory(parent)) {
            Files.createDirectories(parent);
        }
        if (!Files.isRegularFile(path)) {
            Files.createFile(path);
        }

        ArrayList<Task> tasks = new ArrayList<>();
        BufferedReader reader = Files.newBufferedReader(path);
        String line = reader.readLine();
        while (line != null) {
            tasks.add(textToTask(line));
            line = reader.readLine();
        }
        reader.close();
        return tasks;
    }

    private String taskToText(Task task) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String date;
        if (task.getDate() != LocalDateTime.MAX) {
            date = task.getDate().format(formatter);
            date = date.replace("T", " ");
            date = date.substring(0, 16);
        } else {
            date = "no date";
        }
        return task.getType() + " , " + task.getStatusIcon() + " , " + task.getDescription() + " , " + date;
    }

    
    /** 
     * @param text
     * @return Task
     */
    private Task textToTask(String text) {
        String[] textArray = text.split(" , "); 
        String type = textArray[0];
        String isDone = textArray[1];
        String description = textArray[2];
        String date = textArray[3];
        LocalDateTime dateTime = null;
        if (!date.contains("no date")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            dateTime = LocalDateTime.parse(date, formatter);
        }  
        Task task = new Task();
        switch (type) {
            case "T":
                task = new Todo(description);
                break;   
            case "D":
                task = new Deadline(description, dateTime);
                break;
            case "E":
                task = new Event(description, dateTime);
                break;   
            default:
                break;
        }
        if (isDone.contains("X")) {
            task.markAsDone();
        }
        return task;
    }
}