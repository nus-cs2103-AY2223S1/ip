package duke;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> arr) {
        this.taskList = arr;
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public void add(Task task) {
        this.taskList.add(task);
        Parser.listToFile(this.taskList);
    }

    public void remove(int index) {
        this.taskList.remove(index);
        Parser.listToFile(this.taskList);
    }

    public int size() {
        return this.taskList.size();
    }

    public void mark(int index) {
        this.taskList.get(index).mark();
        Parser.listToFile(this.taskList);
    }

    public void unMark(int index) {
        this.taskList.get(index).unMark();
        Parser.listToFile(this.taskList);
    }

    public void clear() {
        this.taskList.clear();
        Parser.listToFile(this.taskList);
    }

}