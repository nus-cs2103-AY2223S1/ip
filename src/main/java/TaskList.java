import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> inputs = new ArrayList<Task>();


    TaskList() {

        this.inputs = new ArrayList<>();
    }

    public boolean isEmpty() {
        return this.inputs.isEmpty();
    }

    public void addTask(Task task) {

        this.inputs.add(task);
    }

    public void removeTask(int id) {

        this.inputs.remove(id);
    }
    public int getSize() {

        return this.inputs.size();
    }

    public Task getTask(int i) {

        return this.inputs.get(i);
    }

}
