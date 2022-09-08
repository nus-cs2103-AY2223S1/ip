import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    TaskList() {
        tasks = new ArrayList<Task>();
    }

    TaskList(ArrayList<? extends Task> tasks) {
        this.tasks = new ArrayList<Task>();
        this.tasks.addAll(tasks); //deep copy ArrayList
    }

    @Override
    public String toString() {
        String output = "";
        int count = 1;
        if (tasks.size() == 0) {
            return "No tasks! Yay!";
        }
        for (Task task : tasks) {
            output += String.valueOf(count) + ". " + task + "\n";
            count += 1;
        }
        return output;
    }
}