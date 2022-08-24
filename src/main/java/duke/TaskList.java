package duke;

import duke.Task;

import java.util.ArrayList;

public class TaskList {

    int count = 0;
    private ArrayList<Task> arr = new ArrayList<>();


    //constructor
    public TaskList() {

    }

    //put stuff into arr
    public void add(Task s) {
        arr.add(s);
    }

    public Task get(int index) {
        return arr.get(index);
    }

    public Task remove(int index) {
        return arr.remove(index);
    }

    public ArrayList<Task> getArr() {
        return arr;
    }

    public int length() {
        return arr.size();
    }
}
