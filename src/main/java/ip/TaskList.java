package ip;

import ip.exception.NoTaskFound;
import ip.task.Task;

import java.util.LinkedList;

public class TaskList {
    public LinkedList<Task> tasks = new LinkedList<Task>();

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int index) throws NoTaskFound {
        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NoTaskFound(index);
        }
    }

    public void mark(int index) throws NoTaskFound {
        try {
            tasks.get(index).mark();
        } catch (IndexOutOfBoundsException e) {
            throw new NoTaskFound(index);
        }
    }

    public void unmark(int index) throws NoTaskFound {
        try {
            tasks.get(index).unmark();
        } catch (IndexOutOfBoundsException e) {
            throw new NoTaskFound(index);
        }
    }

    @Override
    public String toString() {
        int taskCount = tasks.size();
        return String.format("You have %d tasks. Get started now!\n", taskCount);
    }
}
