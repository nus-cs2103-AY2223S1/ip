import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Adds a task to the task list
     * 
     * @param task Task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public String toString() {
        String output = "";
        // Add task descriptions
        for (int i = 0; i < tasks.size(); i++) {
            output += (i + 1);
            output += " ";
            output += tasks.get(i);
            output += "\n";
        }
        return output != "" ? output : "No tasks!";
    }
}
