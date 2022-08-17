import java.util.ArrayList;

public class CheckList {
    // Class Fields
    private ArrayList<Task> tasks;
    private int taskCount;

    // Constructor
    public CheckList() {
        tasks = new ArrayList<Task>();
        taskCount = 1;
    }

    // Methods
    public void addTask(Task task) {
        tasks.add(task);
        taskCount++;
    }

    public int getTaskCount() {
        return this.taskCount;
    }

    public String printList() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); ++i) {
            if (i == tasks.size() - 1) {
                output.append(tasks.get(i).toString());
            } else {
                output.append(tasks.get(i).toString()).append("\n");
            }
        }
        return output.toString();
    }
}