import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public void add(Task task) {
        this.listOfTasks.add(task);
    }

    public void remove(int index) {
        this.listOfTasks.remove(index - 1);
    }

    public int getSize() {
        return this.listOfTasks.size();
    }

    public Task getTask(int index) { return this.listOfTasks.get(index); }

    public void markAsDone(int index) {
        this.listOfTasks.get(index).markAsDone();
    }

    public void markAsNotDone(int index) {
        this.listOfTasks.get(index).markAsNotDone();
    }
}
