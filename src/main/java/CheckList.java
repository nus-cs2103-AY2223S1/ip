import java.util.ArrayList;

public class CheckList {
    // Class Fields
    protected ArrayList<Task> tasks;

    // Constructor
    public CheckList() {
        tasks = new ArrayList<>();
    }

    // Methods
    public void addTask(Task task) {
        tasks.add(task);
    }

    public String printList() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); ++i) {
            Task curr = tasks.get(i);
            if (i == tasks.size() - 1) {
                output.append(i + 1).append(". ").append(curr.toString());
            } else {
                output.append(i + 1).append(". ").append(curr.toString()).append("\n");
            }
        }
        return output.toString();
    }

    public String printTaskStatus(int idx) {
        Task curr = tasks.get(idx);
        return curr.toString();
    }
}