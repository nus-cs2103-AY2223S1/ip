package duke.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.data.exception.DukeException;
import duke.tasks.Task;

/**
 * This class encapsulates a list of tasks
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new Task List
     * @param tasks The list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * To list down all the tasks that are added to the list.
     * @return A list of all the tasks added.
     */
    public ArrayList<Task> list() {
        return this.tasks;
    }

    /**
     * Gets all the task that falls on the specified date
     * @param date Date of the tasks
     * @return A string consisting of all the tasks
     */
    public ArrayList<Task> getTasks(String date) {
        LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTaskType().equals("D") || task.getTaskType().equals("E")) {
                if (task.getDate().equals(parsedDate)) {
                    list.add(task);
                }
            }
        }

        return list;
//        LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//        StringBuilder stringBuilder = new StringBuilder("\tYour tasks for today include:");
//        int count = 1;
//        for (Task task : this.tasks) {
//            if (task.getTaskType().equals("D") || task.getTaskType().equals("E")) {
//                if (task.getDate().equals(parsedDate)) {
//                    String formatted = String.format("\n\t%d. %s", count, task);
//                    stringBuilder.append(formatted);
//                    count++;
//                }
//            }
//        }
//
//        if (count == 1) {
//            return String.format("\tNo tasks on %s", parsedDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
//        } else {
//            return stringBuilder.toString();
//        }
    }

    /**
     * Adds a task to the list
     * @param task The task to be added
     */
    public void addToList(Task task) {
        this.tasks.add(task);
    }

    /**
     * Gets the number of tasks in the list
     * @return The number of tasks in the list
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Gets the task at the specified index
     * @param i Index of the task
     * @return The task with the specified index
     * @throws DukeException If the task does not exist
     */
    public Task getTask(int i) throws DukeException {
        if (i > this.tasks.size() || i < 0) {
            throw new DukeException("No such task exists!");
        }
        return this.tasks.get(i);
    }

    /**
     * Deletes the task at the specified index
     * @param i The index of the task
     * @return The deleted task
     * @throws DukeException If the task does not exist
     */
    public Task deleteTask(int i) throws DukeException {
        if (i > this.tasks.size() || i < 0) {
            throw new DukeException("No such task exist!");
        }
        Task task = this.tasks.get(i - 1);
        this.tasks.remove(i - 1);
        return task;
    }

    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(keyword)) {
                list.add(task);
            }
        }

        return list;
    }
}
