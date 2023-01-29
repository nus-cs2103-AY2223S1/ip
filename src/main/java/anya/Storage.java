package anya;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
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
    public ArrayList<Task> loadCurrentTasksFromFile() throws IOException {
        ArrayList<Task> currentTasks = new ArrayList<>();

        File savedTask = getDataFile("data/", this.filePath);

        Scanner sc = new Scanner(savedTask);
        while (sc.hasNextLine()) {
            // Current tasks are prefixed with "Current"
            if (sc.hasNext("Current")) {
                String line = sc.nextLine();
                String entry = line.split(" ", 2)[1];
                currentTasks.add(readEntry(entry));
            } else {
                sc.nextLine();
            }
        }

        return currentTasks;
    }

    public ArrayList<Task> loadDeletedTasksFromFile() throws IOException {
        ArrayList<Task> deletedTasks = new ArrayList<>();

        File savedTask = getDataFile("data/", this.filePath);

        Scanner sc = new Scanner(savedTask);
        while (sc.hasNextLine()) {
            // Deleted tasks are prefixed with "Deleted"
            if (sc.hasNext("Deleted")) {
                String line = sc.nextLine();
                String entry = line.split(" ", 2)[1];
                deletedTasks.add(readEntry(entry));
            } else {
                sc.nextLine();
            }
        }
        return deletedTasks;
    }

    public LocalDate loadDateCreatedFromFile() throws IOException {
        LocalDate dateCreated = LocalDate.now();

        File savedTask = getDataFile("data/", this.filePath);

        Scanner sc = new Scanner(savedTask);
        // Get date stored in database if database exists; get today's date otherwise
        while (sc.hasNextLine()) {
            if (sc.hasNext("Date")) {
                String line = sc.nextLine();
                String date = line.split(" ", 2)[1];
                dateCreated = LocalDate.parse(date);
                break;
            } else {
                sc.nextLine();
            }
        }
        return dateCreated;
    }

    public File getDataFile(String directory, String filePath) throws IOException {
        // Create new directory if it does not exist
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File savedTask = new File(filePath);
        if (!savedTask.exists()) {
            savedTask.createNewFile();
        }

        return savedTask;
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
            assert taskType.equals("E") : "The taskType should be E. It is currently " + taskType;
            String taskName = arrStr[2].split(" \\| ")[0];
            String time = arrStr[2].split(" \\| ")[1];
            task = new Event(taskName, time);
        }
        if (isTaskDone) {
            task.markDone();
            assert task.getStatusIcon().equals("X") : "Task should be marked as done";
        }
        return task;
    }

    public void saveFile(TaskList tasks) throws IOException {
        FileWriter saveTask = new FileWriter(this.filePath);
        // Store TaskList created date on first line. Prefixed with "Date".
        saveTask.write("Date " + tasks.getDateCreated() + "\n");

        // Store current tasks in subsequent lines. Prefixed with "Current"
        for (int i = 0; i < tasks.getLength(); i++) {
            Task task = tasks.getTaskFromIndex(i + 1);
            saveTask.write("Current " + task.toSaveFormat() + "\n");
        }

        // Store deleted tasks in subsequent lines. Prefixed with "Deleted"
        for (int i = 0; i < tasks.getDeletedTasksLength(); i++) {
            Task task = tasks.getDeletedTaskFromIndex(i + 1);
            saveTask.write("Deleted " + task.toSaveFormat() + "\n");
        }
        saveTask.close();
    }
}
