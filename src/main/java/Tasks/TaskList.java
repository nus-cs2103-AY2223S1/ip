package tasks;

import exception.LunaException;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) throws LunaException {
        if (tasks == null) {
            throw new LunaException("Luna did not find any tasks ⛈");
        }
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return this.tasks.size();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int num) {
        Task removed = this.tasks.get(num);
        this.tasks.remove(num);
        return removed;
    }

    public Task mark(int num) {
        this.tasks.get(num).setStatusIcon(true);
        return this.tasks.get(num);
    }

    public Task unmark(int num) {
        this.tasks.get(num).setStatusIcon(false);
        return this.tasks.get(num);
    }

    public String stored() {
        String ls = "";
        for(int i = 0; i < tasks.size(); i++) {
            ls += "\n       " + tasks.get(i);
        }
        return ls;
    }

    public String find(String txt) {
        String result = "";
        int index = 1;
        for(int i = 0; i < size(); i++) {
            Task curr = tasks.get(i);
            if ((curr.toString().substring(7)).contains(txt)) {
                result += "\n" + index++ + ". " + curr;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String list = "";
        for(int i = 0; i < tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                list += i + 1 + ". " + tasks.get(i);
            } else {
                list += i + 1 + ". " + tasks.get(i) + "\n";
            }
        }
        return list;
    }
}
