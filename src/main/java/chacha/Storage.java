package chacha;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.BufferedReader;
import chacha.tasks.Deadline;
import chacha.tasks.Event;
import chacha.tasks.Task;
import chacha.tasks.Todo;

public class Storage {
    private Path path;

    public Storage(String path) {
        this.path = Paths.get(path);
    }

    public void saveToFile(TaskList tasks) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(path);
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.get(i);
            writer.write(taskToText(task));
            writer.newLine();
        }
        writer.close();
    }

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
        return task.getType() + " , " + task.getStatusIcon() + " , " + task.getDescription() + " , " + task.getDate();
    }

    private Task textToTask(String text) {
        String[] textArray = text.split(" , "); 
        String type = textArray[0];
        String isDone = textArray[1];
        String description = textArray[2];
        String date = textArray[3];
        Task task = new Task();
        switch (type) {
            case "T":
                task = new Todo(description);
                break;   
            case "D":
                task = new Deadline(description, date);
                break;
            case "E":
                task = new Event(description, date);
                break;   
            default:
                break;
        }
        if (isDone.contains("X")) {
            System.out.println("marking as done");
            task.markAsDone();
            
        }
        return task;
    }
}