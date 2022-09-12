package duke.entities;

import java.util.ArrayList;
import java.util.Iterator;

/** Tasklist entity. */
public class Tasklist implements Iterable<Task> {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a new Task to the Tasklist.
     *
     * @param task Task
     */
    public void add(Task task) {
        this.tasks.add(task);
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
