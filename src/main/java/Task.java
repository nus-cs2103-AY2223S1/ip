import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;
    private static ArrayList<Task> tasks = new ArrayList<>();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public static void add(Task task) {
        tasks.add(task);
    }

    public static void listTasks() {
        for (int i = 1; i < tasks.size(); i++) {
            System.out.println(String.format("\t%d. %s", i, tasks.get(i)));
        }
    }

    public static int count() {
        return tasks.size() - 1; // since we always have one dummy Task to make it 1-indexed
    }

    public static Task get(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public static void markAsDone(int taskNumber) {
        if (taskNumber < 1 || taskNumber > Task.count()) {
            System.out.println("\tInvalid Task Number!");
        } else {
            Task.get(taskNumber).markAsDone();
            System.out.println("\tAwesome! I have marked this task as done:");
            System.out.println("\t\t"+Task.get(taskNumber));
        }
    }

    public static void markAsNotDone(int taskNumber) {
        if (taskNumber < 1 || taskNumber > Task.count()) {
            System.out.println("\tInvalid Task Number!");
        } else {
            Task.get(taskNumber).markAsNotDone();
            System.out.println("\tAwesome! I have unmarked this task to be not completed:");
            System.out.println("\t\t"+Task.get(taskNumber));
        }
    }
}
