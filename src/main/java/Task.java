import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task {
    private static final String FILE_LOCATION = "./data/TaskFile.txt";
    private static final int ISDONE_MARKER = 4;
    private static final int DESC_MARKER = 8;


    private static ArrayList<Task> tasks = new ArrayList<Task>();

    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public static void setTasks(ArrayList<Task> tasks) {
        Task.tasks = tasks;
    }

    public static ArrayList<Task> getTasks() {
        return Task.tasks;
    }

    public static void addTask(Task task) {
        Task.tasks.add(task);
    }

    public static void removeTask(int index) {
        BotResponse.removeTaskLog(Task.tasks.get(index));
        Task.tasks.remove(index);
    }

    public static int length() {
        return Task.tasks.size();
    }

    private String getStatus() {
        return (this.isDone ? "X" : " ");
    }

    private String getStatusData() {
        return (this.isDone ? "1" : "0");
    }

    public String toString() {
        return "[" + this.getStatus() + "] " + this.description;
    }

    public String toStringData() {
        return getStatusData() + " | " + this.description;
    }

    public static void markDone(int index, boolean done) {
        Task.tasks.get(index).isDone = done;
        BotResponse.markLog(Task.tasks.get(index), done);
    }

    public static void printTasks() {
        BotResponse.separationLine();
        for (int i = 0; i < Task.tasks.size(); i++) {
            System.out.println((i + 1) + "." + Task.tasks.get(i));
        }
        BotResponse.separationLine();
    }
}
