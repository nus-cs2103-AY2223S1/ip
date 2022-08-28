package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask(Task task) {
        if (task instanceof Todo) {
            this.list.add(this.list.size(), task);
            System.out.println("Added ToDo: " + task);
        } else if (task instanceof Deadline) {
            this.list.add(this.list.size(), task);
            System.out.println("Added Deadline: " + task);
        } else if (task instanceof Event) {
            this.list.add(this.list.size(), task);
            System.out.println("Added Event: " + task);
        }
    }
    public void deleteTask(int ind) throws DukeException {
        if (ind > this.list.size() - 1) {
            throw new DukeException("Oops, no such task to delete.");
        } else {
            System.out.println("Task removed: " + this.list.get(ind));
            this.list.remove(ind);
            System.out.println(this.list.size() + " tasks remaining.");
        }
    }
    public void markTask(int ind, boolean done) throws DukeException {
        if (ind > this.list.size() - 1) {
            throw new DukeException("Oops, no such task found.");
        } else if (done) {
            this.list.get(ind).markDone();
            System.out.println("Task done: " + this.list.get(ind));
        } else {
            this.list.get(ind).markNotDone();
            System.out.println("Task not done: " + this.list.get(ind));
        }
    }
    public void getDateTasks(String dateStr) {
        System.out.println("Tasks on date " + dateStr + ":");
        for (Task t : this.list) {
            if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                if (d.isOnDate(dateStr)) {
                    System.out.println(t);
                }
            } else if (t instanceof Event) {
                Event e = (Event) t;
                if (e.isOnDate(dateStr)) {
                    System.out.println(t);
                }
            }
        }
    }

    public void showList() {
        System.out.println("List of tasks:");
        for (int i = 1; i < this.list.size() + 1; i++) {
            System.out.println(i + ". " + this.list.get(i - 1));
        }
    }

    public ArrayList<Task> getList() {
        return this.list;
    }
}
