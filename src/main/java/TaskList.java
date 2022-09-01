import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;
    protected int numOfTasks;

    public TaskList() {
        tasks = new ArrayList<>();
        numOfTasks = 0;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.numOfTasks = tasks.size();
    }

    public int getNumOfTasks() {
        return numOfTasks;
    }

    public Task getTask(int index) {
        return (Task) tasks.get(index);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }
}
