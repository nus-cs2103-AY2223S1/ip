import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void mark(int num) {
        //ui.printLine();
        tasks.get(num - 1).mark();
        System.out.println("OK, I've marked this task as done:");
        System.out.println(tasks.get(num - 1).toString());
        //ui.printLine();
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int n) {
        return tasks.get(n);
    }

    public void remove(int n) {
        tasks.remove(n);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
