package duke;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

public class TaskList {
    private ArrayList<Task> arrayList;

    public TaskList() {
        this.arrayList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.arrayList;
    }

    public void add(Task task) {
        this.arrayList.add(task);
    }

    public void list() {
        int counter = 1;
        for (Task task : arrayList) {
            System.out.println(counter + ". " + task.toString());
            counter++;
        }
    }

    public Task get(int index) {
        return this.arrayList.get(index);

    }

    public Task set(int index, Task task) {
        return this.arrayList.set(index, task);
    }

    public Task remove(int index) {
        return this.arrayList.remove(index);
    }

    public int size() {
        return this.arrayList.size();
    }

    public void forEach(Consumer consumer) {
        arrayList.forEach(consumer);
    }


}
