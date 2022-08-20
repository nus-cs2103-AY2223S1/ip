import java.util.ArrayList;

public class Task {
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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

    public String toString() {
        return "[" + this.getStatus() + "] " + this.description;
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
