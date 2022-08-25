package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    public ArrayList<Task> getTaskArr() {
        return this.lst;
    }


    public void showList() {
        if (!lst.isEmpty()) {
            for (int i = 0; i < lst.size(); i++) {
                System.out.println("\t" + (i + 1) + "." + lst.get(i));
            }
        } else { // list is empty
            Ui.show("duke.Task list is empty!");
        }
    }

    public boolean isEmpty() {
        return lst.isEmpty();
    }

    public Task get(int index) {
        return lst.get(index);
    }

    public void add(Task t) {
        lst.add(t);
    }

    public int size() {
        return lst.size();
    }

    public void remove(int index) {
        lst.remove(index);
    }

    public TaskList find(String s) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task t : lst) {
            if (t.toString().contains(s)) {
                res.add(t);
            }
        }
        return new TaskList(res);
    }
}
