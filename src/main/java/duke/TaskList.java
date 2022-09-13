package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    private final String HORIZONTAL_LINE_BREAK = "-------------------------";

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markTask(int index) throws DukeException {
        int size = tasks.size();
        if (index >= size || index < 0) {
            throw new DukeException("The index of the task does not exists.");
        } else {
            Task completedTask = tasks.get(index);
            completedTask.markAsDone();
            System.out.println(HORIZONTAL_LINE_BREAK);
            System.out.println("Nice! I've marked this task as done:" + "\n" + completedTask);
            System.out.println(HORIZONTAL_LINE_BREAK);
        }
    }

    public void unMarkTask(int index) throws DukeException{
        int size = tasks.size();
        if (index >= size || index < 0) {
            throw new DukeException("The index of the task does not exists.");
        } else {
            Task unfinishedTask = tasks.get(index);
            unfinishedTask.markAsNotDone();
            System.out.println(HORIZONTAL_LINE_BREAK);
            System.out.println("OK, I've marked this task as not done yet:" + "\n" + unfinishedTask);
            System.out.println(HORIZONTAL_LINE_BREAK);
        }
    }

    public void deleteTask(int index) throws DukeException {
        int size = tasks.size();
        if (index >= size || index < 0) {
            throw new DukeException("The index of the task does not exists.");
        } else {
            Task toBeDeleted = tasks.get(index);
            tasks.remove(index);
            System.out.println(HORIZONTAL_LINE_BREAK);
            System.out.println("Noted. I've removed this task:" + "\n" + toBeDeleted + "\n" + "Now you have " + tasks.size()
                    + " tasks in your list.");
            System.out.println(HORIZONTAL_LINE_BREAK);
        }
    }
}
