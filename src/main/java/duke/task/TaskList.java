package duke.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.exception.DukeException;
import duke.exception.InvalidTaskIndexException;

public class TaskList {
    private final ArrayList<Task> tasks;
    private final Path storage;
    public enum TaskType {
        DEADLINE, EVENT, TODO
    }

    public TaskList(Path storagePath) {
        this.tasks = new ArrayList<>();
        this.storage = storagePath;
        try {
            Files.createDirectories(storagePath.getParent());
            if (Files.exists(storagePath)) {
                this.readTasksFromStorage();
            } else {
                Files.createFile(storagePath);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readTasksFromStorage() {
        try {
            List<String> lines = Files.readAllLines(this.storage);
            for (String task : lines) {
                String[] taskSplit = task.split(" \\| ");
                Task newTask;
                switch (taskSplit[0]) {
                case "T":
                    newTask = new Todo(taskSplit[2]);
                    break;
                case "D":
                    newTask = new Deadline(taskSplit[2], taskSplit[3]);
                    break;
                case "E":
                    newTask = new Event(taskSplit[2], taskSplit[3]);
                    break;
                default:
                    throw new DukeException("Invalid task description in storage: " + task);
                }
                if (taskSplit[1].equals("1")) {
                    newTask.markAsDone();
                }
                this.tasks.add(newTask);
            }
        } catch (IOException e) {
            System.out.println("Error reading from " + this.storage + ": " + e.getMessage());
        }
    }

    public void writeTasksToStorage() {
        try {
            Files.write(this.storage, this.tasks.stream().map(Task::toStorage).collect(Collectors.toList()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addTask(Task t) {
        this.tasks.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
        this.writeTasksToStorage();
    }

    public void addTask(TaskType taskType, String desc) {
        Task newTask;
        switch (taskType) {
        case TODO:
            newTask = new Todo(desc);
            break;
        case DEADLINE: {
            String[] descSplit = desc.split(" /by ", 2);
            if (descSplit.length > 1) {
                newTask = new Deadline(descSplit[0], descSplit[1]);
            } else {
                throw new DukeException("Date/time not specified for deadline");
            }
            break;
        }
        case EVENT: {
            String[] descSplit = desc.split(" /at ", 2);
            if (descSplit.length > 1) {
                newTask = new Event(descSplit[0], descSplit[1]);
            } else {
                throw new DukeException("Date/time not specified for event");
            }
            break;
        }
        default:
            throw new DukeException("Unrecognized task type: " + taskType);
        }
        this.addTask(newTask);
    }

    public void deleteTask(int taskIndex) {
        if (taskIndex < 1 || taskIndex > this.tasks.size()) {
            throw new InvalidTaskIndexException(taskIndex);
        }
        Task t = this.tasks.remove(taskIndex - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
        this.writeTasksToStorage();
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println(i + 1 + "." + this.tasks.get(i));
        }
    }

    public void markTaskAsDone(int taskIndex) {
        if (taskIndex < 1 || taskIndex > this.tasks.size()) {
            throw new InvalidTaskIndexException(taskIndex);
        }
        this.tasks.get(taskIndex - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + this.tasks.get(taskIndex - 1));
        this.writeTasksToStorage();
    }

    public void markTaskAsNotDone(int taskIndex) {
        if (taskIndex < 1 || taskIndex > this.tasks.size()) {
            throw new InvalidTaskIndexException(taskIndex);
        }
        this.tasks.get(taskIndex - 1).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + this.tasks.get(taskIndex - 1));
        this.writeTasksToStorage();
    }
}
