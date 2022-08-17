import java.util.ArrayList;

public class CheckList {
    // Class Fields
    protected ArrayList<Task> tasks;
    protected int taskCount;

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
            Task curr = tasks.get(i);
            if (i == tasks.size() - 1) {
                output.append(i + 1)
                        .append(".[")
                        .append(curr.getStatusIcon())
                        .append("] ")
                        .append(curr);
            } else {
                output.append(i + 1)
                        .append(".[")
                        .append(curr.getStatusIcon())
                        .append("] ")
                        .append(curr)
                        .append("\n");
            }
        }
        return output.toString();
    }

    public String printTaskStatus(int idx) {
        StringBuilder output = new StringBuilder();
        Task curr = tasks.get(idx);
        output.append("[")
                .append(curr.getStatusIcon())
                .append("] ")
                .append(curr.toString());
        return output.toString();
    }
}