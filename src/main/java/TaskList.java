import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Returns the Task at the given indx
     * 
     * @param indx Specifies the Task to return
     * @return The task at index
     */
    public Task get(int indx) {
        return tasks.get(indx);
    }

    /**
     * Adds a task to the task list
     * 
     * @param task Task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int indx) {
        return tasks.remove(indx);
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
