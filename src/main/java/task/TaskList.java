package task;

import duke.DukeException;
import task.Task;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> taskArray;

    protected String list;

    protected int size;

    public TaskList(ArrayList<Task> arr) {
        this.taskArray = arr;
        this.size = arr.size();
    }

    public TaskList() {
        ArrayList<Task> task = new ArrayList<Task>(100);
        this.taskArray = task;
        this.size = 0;
    }

    public String printContent() throws DukeException {
        String out = "";
        try {
            for (int i = 0; i < 2; i++) {
                out = out + taskArray.get(i).toString() + "\n";
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("");
        }
        return out;
    }

    public int taskListSize() {
        return size;
    }

    public void add(Task task) {
        this.taskArray.add(task);
        System.out.println("----------------------\n" + "Ok Solid you got this work to do:\n" +
                task.toString() + String.format("\nYou have a total of %d work to do", size + 1)
                + "\n----------------------\n");
        size++;
    }

    public void addStart(Task task) {
        this.taskArray.add(task);
        size++;
    }

    public void delete(int index) {
        String removed = taskArray.get(index).toString();
        taskArray.remove(index);
        System.out.println("----------------------\n" + "Noted, The following task has been removed\n" +
                removed + String.format("\nYou have a total of %d work to do still", size - 1) +
                "\n----------------------\n");
        size--;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskArray;
    }

    public String getList() {
        String o = "";
        for (int i = 0; i < size; i++) {
            o = o + String.format("%d", i + 1) + "." + taskArray.get(i) + "\n";
        }
        this.list = o;
        return this.list;
    }



}
