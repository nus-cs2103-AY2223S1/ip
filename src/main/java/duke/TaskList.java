package duke;

import java.util.ArrayList;

import duke.command.Command;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

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
    public String addTask(Task task) throws DukeException {
        if (task instanceof Todo) {
            this.tasks.add(this.tasks.size(), task);
            return "Added ToDo: " + task;
        } else if (task instanceof Deadline) {
            this.tasks.add(this.tasks.size(), task);
            return "Added Deadline: " + task;
        } else if (task instanceof Event) {
            this.tasks.add(this.tasks.size(), task);
            return "Added Event: " + task;
        } else {
            throw new DukeException("Oops, unknown task type.");
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param ind index of the task to be deleted
     * @throws DukeException if index is invalid
     */
    public String deleteTask(int ind) throws DukeException {
        if (ind > this.tasks.size() - 1) {
            throw new DukeException("Oops, no such task to delete.");
        } else {
            System.out.println("Task removed: " + this.tasks.get(ind));
            this.tasks.remove(ind);
            return this.tasks.size() + " tasks remaining.";
        }
    }

    /**
     * Marks a task as done or not done.
     *
     * @param ind index of the task to be marked
     * @param done true if task is to be marked as done, false if it is to be marked as not done
     * @throws DukeException if index is invalid
     */
    public String markTask(int ind, boolean done) throws DukeException {
        if (ind > this.tasks.size() - 1) {
            throw new DukeException("Oops, no such task found.");
        } else if (done) {
            this.tasks.get(ind).setDone(true);
            return "Task done: " + this.tasks.get(ind);
        } else {
            this.tasks.get(ind).setDone(false);
            return "Task not done: " + this.tasks.get(ind);
        }
    }

    /**
     * Prints the tasks relevant to a given date.
     *
     * @param dateStr the given date
     */
    public String getDateTasks(String dateStr) {
        String response = "Tasks on date " + dateStr + ":";
        for (Task t : this.tasks) {
            if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                if (d.isOnDate(dateStr)) {
                    response += "\n" + t;
                }
            } else if (t instanceof Event) {
                Event e = (Event) t;
                if (e.isOnDate(dateStr)) {
                    response += "\n" + t;
                }
            }
        }
        return response;
    }

    /**
     * Finds the tasks related to a given keyword.
     *
     * @param keyword the keyword
     */
    public String getSearchResults(String keyword) {
        String response = "Matching tasks for \"" + keyword + "\":";
        for (Task t : this.tasks) {
            if (t.hasKeyword(keyword)) {
                response += "\n" + t;
            }
        }
        return response;
    }

    /**
     * Prints out the entire list of tasks.
     */
    public String showList() {
        String response = "List of tasks:";
        for (int i = 1; i < this.tasks.size() + 1; i++) {
            response += "\n" + (i + ". " + this.tasks.get(i - 1));
        }
        return response;
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
