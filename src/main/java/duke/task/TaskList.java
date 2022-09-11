package duke.task;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskArray;
    private int count = 0;

    public TaskList() {
        this.taskArray = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskArray) {
        this.taskArray = taskArray;
    }

    public void add(Task task) {
        this.taskArray.add(task);
        this.count += 1;
    }

    public Task getTask(int position) throws IndexOutOfBoundsException {
        return taskArray.get(position - 1);
    }

    public int getCount() {
        return this.count;
    }

    public void markTaskAtPos(int position) throws IndexOutOfBoundsException {
            Task currTask = getTask(position);
            currTask.markAsDone();
    }

    public boolean unmarkTaskAtPos(int position) {
        try {
            Task currTask = getTask(position);
            currTask.unmark();
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public Task deleteTaskAtPos(int position) throws IndexOutOfBoundsException {
        Task deletedTask = getTask(position);
        this.taskArray.remove(position - 1);
        this.count -= 1;
        return deletedTask;
    }

    public String toSimpleStrings() {
        String stringedList = "";
        for (int i = 0; i < this.count; i++) {
            stringedList += getTask(i + 1).toSimpleString() + "\n";
        }
        return stringedList;
    }

    @Override
    public String toString() {
        String stringedList = "";
        for (int i = 0; i < this.count; i++) {
            if (i == this.count -1) {
                stringedList += (i + 1) + ". " + getTask(i + 1).toString();
            } else {
                stringedList += (i + 1) + ". " + getTask(i + 1).toString() + "\n";
            }
        }
        return "Here are the tasks in your list:\n" + stringedList;
    }

}
