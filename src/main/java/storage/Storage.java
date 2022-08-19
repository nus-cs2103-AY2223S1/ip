package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import printer.Printer;

public class Storage {
    private List<Task> tasks;
    private final String filePath;

    public Storage(String directoryName, String fileName) {
        this.filePath = directoryName + "/" + fileName;
        this.tasks = new ArrayList<>();

        try {
            File storageDirectory = new File(directoryName);

            if (!storageDirectory.exists()) {
                storageDirectory.mkdir();
            }

            File storageFile = new File(this.filePath);

            if (storageFile.exists()) {
                loadTasksInStorage();
            } else {
                storageFile.createNewFile();
            }
        } catch (IOException error) {
            Printer.print(String.format("Failed to load tasks, %s", error.getMessage()));
        }

    }

    private void loadTasksInStorage() throws IOException {
        List<Task> loadedTasks = new ArrayList<>();
        BufferedReader storageReader = new BufferedReader(new FileReader(this.filePath));
        String taskString = storageReader.readLine();

        while(taskString != null) {
            int dateSeparatorIndex = taskString.lastIndexOf("|");
            String taskType = taskString.substring(0, 1);
            String taskStatus = taskString.substring(2, 3);
            String description = dateSeparatorIndex == 3
                    ? taskString.substring(4)
                    : taskString.substring(4, dateSeparatorIndex);
            String taskDate = taskString.substring(dateSeparatorIndex + 1);

            Task currentLoadedTask = taskType.equals("D")
                    ? new Deadline(description, taskDate)
                    : taskType.equals("E")
                    ? new Event(description, taskDate)
                    : new ToDo(description);

            if (taskStatus.equals("X")) {
                currentLoadedTask.markAsFinished();
            }

            loadedTasks.add(currentLoadedTask);
            taskString = storageReader.readLine();
        }

        this.tasks = loadedTasks;
        storageReader.close();
    }

    private void saveTasksInStorage() {
        try {
            FileWriter storageWriter = new FileWriter(this.filePath);
            for (Task task : tasks) {
                storageWriter.write(task.toStorageRepresentation() + "\n");
            }
            storageWriter.close();
        } catch (IOException error) {
            Printer.print(String.format("Failed to update tasks, %s", error.getMessage()));
        }
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);

        Printer.print(String.format("This task is successfully added:\n %s\n"
                + "Now you have %d task(s) in the list",
                newTask.toString(), this.tasks.size()));
    }

    public void markTaskWithIndex(int index) {
        Task selectedTask = this.tasks.get(index);
        selectedTask.markAsFinished();
        saveTasksInStorage();
        Printer.print(String.format("This task has been marked as done:\n %s",
                selectedTask));
    }

    public void unmarkTaskWithIndex(int index) {
        Task selectedTask = this.tasks.get(index);
        selectedTask.markAsNotFinished();
        saveTasksInStorage();
        Printer.print(String.format("This task has been marked as not done yet:\n %s",
                selectedTask));
    }

    public void removeTaskWithIndex(int index) {
        Task selectedTask = this.tasks.get(index);
        this.tasks.remove(index);
        saveTasksInStorage();
        Printer.print(String.format("Noted. I've removed this task:\n %s\n"
                + "Now you have %d task(s) in the list",
                selectedTask, this.tasks.size()));
    }

    public boolean checkIndex(int index) {
        return index >= 0 && index < this.tasks.size();
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "You have no tasks at the moment.";
        }

        String tasksString = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksString += String.format("%d. %s\n", i + 1, this.tasks.get(i));
        }
        return "Here are the tasks in your list\n" + tasksString;
    }

}
