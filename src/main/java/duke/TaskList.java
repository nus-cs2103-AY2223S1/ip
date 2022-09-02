package duke;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        if (task instanceof Todo) {
            this.tasks.add(this.tasks.size(), task);
            System.out.println("Added ToDo: " + task);
        } else if (task instanceof Deadline) {
            this.tasks.add(this.tasks.size(), task);
            System.out.println("Added Deadline: " + task);
        } else if (task instanceof Event) {
            this.tasks.add(this.tasks.size(), task);
            System.out.println("Added Event: " + task);
        }
    }
    public void deleteTask(int ind) throws DukeException {
        if (ind > this.tasks.size() - 1) {
            throw new DukeException("Oops, no such task to delete.");
        } else {
            System.out.println("Task removed: " + this.tasks.get(ind));
            this.tasks.remove(ind);
            System.out.println(this.tasks.size() + " tasks remaining.");
        }
    }
    public void markTask(int ind, boolean done) throws DukeException {
        if (ind > this.tasks.size() - 1) {
            throw new DukeException("Oops, no such task found.");
        } else if (done) {
            this.tasks.get(ind).setDone(true);
            System.out.println("Task done: " + this.tasks.get(ind));
        } else {
            this.tasks.get(ind).setDone(false);
            System.out.println("Task not done: " + this.tasks.get(ind));
        }
    }
    public void getDateTasks(String dateStr) {
        System.out.println("Tasks on date " + dateStr + ":");
        for (Task t : this.tasks) {
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
        for (int i = 1; i < this.tasks.size() + 1; i++) {
            System.out.println(i + ". " + this.tasks.get(i - 1));
        }
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }
}
