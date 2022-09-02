package duke;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructor method for a TaskList.
     *
     * @param tasks a list of Tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added
     */
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

    /**
     * Deletes a task from the list.
     *
     * @param ind index of the task to be deleted
     * @throws DukeException if index is invalid
     */
    public void deleteTask(int ind) throws DukeException {
        if (ind > this.tasks.size() - 1) {
            throw new DukeException("Oops, no such task to delete.");
        } else {
            System.out.println("Task removed: " + this.tasks.get(ind));
            this.tasks.remove(ind);
            System.out.println(this.tasks.size() + " tasks remaining.");
        }
    }

    /**
     * Marks a task as done or not done.
     *
     * @param ind index of the task to be marked
     * @param done true if task is to be marked as done, false if it is to be marked as not done
     * @throws DukeException if index is invalid
     */
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

    /**
     * Prints the tasks relevant to a given date.
     *
     * @param dateStr the given date
     */
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

    /**
     * Prints out the entire list of tasks.
     */
    public void showList() {
        System.out.println("List of tasks:");
        for (int i = 1; i < this.tasks.size() + 1; i++) {
            System.out.println(i + ". " + this.tasks.get(i - 1));
        }
    }

    /**
     * Returns the list of tasks as an ArrayList.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }
}
