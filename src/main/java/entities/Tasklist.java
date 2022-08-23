package entities;

import java.util.ArrayList;
import java.util.Iterator;

import static utils.Utils.customPrint;

public class Tasklist implements Iterable<Task> {
    private final ArrayList<Task> list = new ArrayList<>();

    public void add(Task task) {
        this.list.add(task);
        int size;
        if ((size = list.size()) == 1) {
            customPrint(String.format("Got it. I've added this task:\n  " +
                    task +
                    "\nNow you have %d task in the list.", size));
        } else {
            customPrint(String.format("Got it. I've added this task:\n  " +
                    task +
                    "\nNow you have %d tasks in the list.", size));
        }
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    public void remove(int index) {
        Task item = this.list.get(index);
        this.list.remove(index);
        customPrint("Noted. I've removed this task:\n" +
                item +
                "Now you have " + this.list.size() + " tasks in the list.");
    }

    public int size() {
        return this.list.size();
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
}
