package zupey.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/** Tasklist entity. */
public class Tasklist implements Iterable<Task> {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs a Tasklist with no tasks.
     */
    public Tasklist() {}

    /**
     * Constructs a Tasklist with given tasks.
     *
     * @param tasks
     */
    public Tasklist(ArrayList<Task> tasks) {
        // TODO DEEP COPY TO UNDO MARK
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a new Task to the Tasklist.
     *
     * @param task Task
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns a new copy of Tasklist.
     *
     * @return new instance of Tasklist with exact same tasks.
     */
    public Tasklist copy() {
        return new Tasklist(this.tasks);
    }

    /**
     * Retrieves a Task given an index.
     *
     * @param index
     * @return Task
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Removes a Task at the given index.
     *
     * @param index
     * @return Task that was removed
     */
    public Task remove(int index) {
        Task item = this.tasks.get(index);
        this.tasks.remove(index);
        return item;
    }

    /**
     * Returns a List of tasks that match the given query.
     *
     * @param query
     * @return List of tasks.
     */
    public List<Task> find(String query) {
        return this
                .tasks
                .stream()
                .filter(t -> t.toString().contains(query))
                .collect(Collectors.toList());
    }

    public int size() {
        return this.tasks.size();
    }

    public Iterator<Task> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator<Task> {

        private int index = 0;

        public boolean hasNext() {
            return index < size();
        }

        public Task next() {
            return get(index++);
        }
    }

    public String toString() {
        return this.tasks.toString();
    }
}
