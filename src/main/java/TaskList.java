import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }
    public int sizeOf() {
        return this.tasks.size();
    }

    public Task getTask(int index) throws PonyException {
        // Check if index is valid
        if (index < 0 || index >= sizeOf()) {
            throw new PonyException("Task index invalid!!");
        }
        return tasks.get(index);
    }

    public int getTasksCount() {
        return tasks.size();
    }


}
