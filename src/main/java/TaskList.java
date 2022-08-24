import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static int size;

    public TaskList () {
        this.tasks = new ArrayList<>();
    }

    public TaskList (ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks () {
        return tasks;
    }

    public int getSize () {
        return tasks.size();
    }

    public Task getTask (int num) {
        return tasks.get(num);
    }

    public void add(Task newTask) {
        tasks.add(newTask);
    }

    public void remove (int num) {
        tasks.remove(num);
    }
}
