package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> list;
    private final String HORIZONTAL_LINE_BREAK = "-------------------------";

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getAllTasks() {
        return list;
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    public int getSize() {
        return list.size();
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public void markTask(int index) throws DukeException {
        int size = list.size();
        if (index >= size || index < 0) {
            throw new DukeException("The index of the task does not exists.");
        } else {
            Task completedTask = list.get(index);
            completedTask.markAsDone();
            System.out.println(HORIZONTAL_LINE_BREAK);
            System.out.println("Nice! I've marked this task as done:" + "\n" + completedTask);
            System.out.println(HORIZONTAL_LINE_BREAK);
        }
    }

    public void unMarkTask(int index) throws DukeException{
        int size = list.size();
        if (index >= size || index < 0) {
            throw new DukeException("The index of the task does not exists.");
        } else {
            Task unfinishedTask = list.get(index);
            unfinishedTask.markAsNotDone();
            System.out.println(HORIZONTAL_LINE_BREAK);
            System.out.println("OK, I've marked this task as not done yet:" + "\n" + unfinishedTask);
            System.out.println(HORIZONTAL_LINE_BREAK);
        }
    }

    public void deleteTask(int index) throws DukeException {
        int size = list.size();
        if (index >= size || index < 0) {
            throw new DukeException("The index of the task does not exists.");
        } else {
            Task toBeDeleted = list.get(index);
            list.remove(index);
            System.out.println(HORIZONTAL_LINE_BREAK);
            System.out.println("Noted. I've removed this task:" + "\n" + toBeDeleted + "\n" + "Now you have " + list.size()
                    + " tasks in your list.");
            System.out.println(HORIZONTAL_LINE_BREAK);
        }
    }
}
