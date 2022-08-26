package anya;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import anya.task.Deadline;
import anya.task.Event;
import anya.task.Task;
import anya.task.TaskList;
import anya.task.Todo;

public class Storage {
    private String filePath;

    // Constructor
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Instance methods
    public ArrayList<Task> loadFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        File dir = new File("data/");
        if (!dir.exists()) {
            dir.mkdir();
        }

        File savedTask = new File(this.filePath);
        if (!savedTask.exists()) {
            savedTask.createNewFile();
        }

        Scanner sc = new Scanner(savedTask);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            tasks.add(readEntry(line));
        }

        return tasks;
    }

    public Task readEntry(String line) {
        Task task;
        String[] arrStr = line.split(" \\| ", 3);
        String taskType = arrStr[0];
        boolean isTaskDone = arrStr[1].equals("1");
        if (taskType.equals("T")) {
            String taskName = arrStr[2];
            task = new Todo(taskName);
        } else if (taskType.equals("D")) {
            String taskName = arrStr[2].split(" \\| ")[0];
            String dateTimeStr = arrStr[2].split(" \\| ")[1];
            task = new Deadline(taskName,
                    LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
        } else {
            String taskName = arrStr[2].split(" \\| ")[0];
            String time = arrStr[2].split(" \\| ")[1];
            task = new Event(taskName, time);
        }
        if (isTaskDone) {
            task.markDone();
        }
        return task;
    }

    public void saveFile(TaskList tasks) throws IOException {
        FileWriter saveTask = new FileWriter(this.filePath);
        for (int i = 0; i < tasks.getLength(); i++) {
            Task task = tasks.getTaskFromIndex(i + 1);
            saveTask.write(task.toSave() + "\n");
        }
        saveTask.close();
    }
}
