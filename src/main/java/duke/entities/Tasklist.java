package duke.entities;

import java.util.ArrayList;
import java.util.Iterator;

public class Tasklist implements Iterable<Task> {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

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
