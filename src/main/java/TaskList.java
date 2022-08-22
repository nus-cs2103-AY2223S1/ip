import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    public void add(Task toAdd) {
        this.tasks.add(toAdd);
    }

    public Task remove(int index) {
        return this.tasks.remove(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public String taskToString(int index) {
        return this.tasks.get(index).toString();
    }

    public String taskSaveToString(int index) {
        return this.tasks.get(index).storedString();
    }

    public void markAsDone(int index) {
        this.tasks.get(index).markDone();
    }

    public void unMarkDone(int index) {
        this.tasks.get(index).unMark();
    }

    @Override
    public String toString() {
        return tasks.toString();
    }
}
