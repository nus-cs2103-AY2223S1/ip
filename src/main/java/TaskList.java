import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public void remove(int index) {
        this.tasks.remove(index);
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            str += String.format("%d. %s \n", i+1, this.tasks.get(i).toString());
        }
        return str;
    }
}
