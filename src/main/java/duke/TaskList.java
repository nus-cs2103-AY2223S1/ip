package duke;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    public void add(Task t) {
        list.add(t);
    }

    public Task get(int i) {
        return list.get(i);
    }

    public void remove(int i) {
        list.remove(i);
    }

    public int size() {
        return list.size();
    }

    public void loadTaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void loadTaskList(Task[] list) {
        this.list = new ArrayList<>();
        for (Task t: list) {
            this.list.add(t);
        }
    }

    /**
     * Enumerates the Task List into a String
     * @return a string enumerating all tasks in list.
     */
    public String toString() {
        String returnMsg = "";
        int index = 1;

        for (Task t : this.list) {
            returnMsg += index + ". " + t + "\n";
            index++;
        }

        return returnMsg;
    }

    public void loadFromLocalStorage(LocalStorage storage) {
        loadTaskList(storage.load());
    }

    public Iterator<Task> getIterator() {
        return this.list.iterator();
    }

}
