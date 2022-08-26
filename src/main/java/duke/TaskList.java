package duke;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    public int count() {
        return lst.size();
    }

    public Task get(int index) {
        return this.lst.get(index);
    }

    public void markTask(int index) {
        Task t = this.lst.get(index);
        t.mark();
        System.out.println("Nice! I've marked this task as done:\n  [X] " + t.description);
    }

    public void unmarkTask(int index) {
        Task t = this.lst.get(index);
        t.unmark();
        System.out.println("OK, I've marked this task as not done yet:\n  [ ] " + t.description);
    }

    public void add(Task t) {
        this.lst.add(t);
    }

    public void delete(Task t) {
        this.lst.remove(t);
    }
}
