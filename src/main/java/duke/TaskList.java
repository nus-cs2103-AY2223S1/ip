package duke;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class TaskList {

    /**
     * Keep track of tasks.
     */

    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void remove(Task task) {
        this.tasks.remove(task);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int itemNumber) {
        return this.tasks.get(itemNumber);
    }

    public int indexOf(Task task) {
        return this.tasks.indexOf(task);
    }

    public void forEach(Consumer<? super Task> action) {
        this.tasks.forEach(action);
    }

    public Stream<Task> stream() {
        return this.tasks.stream();
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }
}
