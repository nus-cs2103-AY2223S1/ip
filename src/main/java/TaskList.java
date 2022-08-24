import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArray;
    private int index;

    public TaskList(ArrayList<Task> taskArray) {
        this.taskArray = taskArray;
        this.index = 0;
    }

    public String printList() {
        String list = "Here are the tasks in your list:\n";
        int size = this.taskArray.size();

        for (int i = 0; i < size; i++) {
            if (this.taskArray.get(i) != null) {
                list += ((i + 1) + "." + this.taskArray.get(i).toString() + '\n');
            }
        }

        return list;
    }

    public void addTask(Task task) {
        this.taskArray.add(this.index, task);
    }

    public void incrementIndex() {
        this.index += 1;
    }

    public void decrementIndex() {
        this.index -= 1;
    }

    public int getIndex() {
        return this.index;
    }

    public Task getTask(int index) {
        return this.taskArray.get(index);
    }

    public Task getTaskAtCurrentIndex() {
        return this.taskArray.get(this.index);
    }

    public void removeTask(int index) {
        this.taskArray.remove(index);
    }

    public void markList(int i) {
        this.taskArray.get(i - 1).markAsDone();

        System.out.println("Nice! I've marked this task as done:\n "
                + this.taskArray.get(i - 1).toString() + '\n');
    }

    public void unMarkList(int i) {
        this.taskArray.get(i - 1).unMarkTask();

        System.out.println("OK, I've marked this task as not done yet:\n "
                + this.taskArray.get(i - 1).toString() + '\n');
    }
}

