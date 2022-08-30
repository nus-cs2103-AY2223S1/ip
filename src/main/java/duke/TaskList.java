package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a task list.
 */
public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs an empty task list.
     */
    TaskList() {

    }

    /**
     * Constructs a task list.
     *
     * @param data Data containing tasks in the list.
     */
    TaskList(String data) {
        if (!data.equals("")) {
            String[] tasksArray = data.split("\n");
            for (String task : tasksArray) {
                this.tasks.add(stringToTask(task));
            }
        }
    }

    private TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private static Task stringToTask(String input) {
        String [] taskDetails = input.split(",");
        String taskDescription = taskDetails[2];
        boolean isDone;
        isDone = taskDetails[1].equals("1");
        if (taskDetails.length == 4) {
            String time = taskDetails[3];
            LocalDate date = LocalDate.parse(time, DateTimeFormatter.ofPattern("MMM dd yyyy"));
            if (taskDetails[0].equals("D")) {
                return new Deadline(taskDescription, isDone, date);
            } else {
                return new Event(taskDescription, isDone, date);
            }
        }
        return new Todo(taskDescription, isDone);
    }

    private static String taskToString(Task task) {
        String taskDescription = task.getDescription();
        String completed = (task.isDone()) ? "1" : "0";
        String type = task.toString().substring(1, 2);
        String [] splitTime = task.toString().split(":");
        if (splitTime.length == 2) {
            String time = splitTime[1];
            return String.join(",", type, completed, taskDescription, time.substring(1, time.length() - 1));
        }
        return String.join(",", type, completed, taskDescription);
    }

    /**
     * Adds task to task list.
     *
     * @param task Target task.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes task from task list.
     *
     * @param ind Index of targeted task.
     * @return deleted task.
     * @throws DukeException if ind >= tasks.size() or ind < 0.
     */
    public Task delete(int ind) throws DukeException {
        try {
            Task deletingTask = this.tasks.get(ind);
            this.tasks.remove(ind);
            return deletingTask;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index given is out of range");
        }
    }

    /**
     * Gets target task from task list.
     *
     * @param i Index of target task.
     * @return target task.
     * @throws DukeException if i >= tasks.size() or i < 0.
     */
    public Task get(int i) throws DukeException {
        try {
            return this.tasks.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index given is out of range");
        }
    }

    /**
     * Returns a task list filtered by a keyword.
     *
     * @param keyword Keyword phrase.
     * @return Filtered task list.
     */
    public TaskList find(String keyword) {
        String[] keywords = keyword.split(" ");
        ArrayList<Task> filteredTasks = new ArrayList<>();
        ArrayList<Integer> added = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            added.add(0);
        }
        for (String word : keywords) {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                if (task.getDescription().contains(word) && added.get(i) == 0) {
                    filteredTasks.add(task);
                    added.set(i, 1);
                }
            }
        }
        return new TaskList(filteredTasks);
    }

    /**
     * Returns size of task list.
     *
     * @return size.
     */
    protected int size() {
        return this.tasks.size();
    }

    /**
     * Changes task list to a string format.
     *
     * @return data.
     */
    public String toString() {
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (i != 0) {
                data.append(System.lineSeparator());
            }
            data.append(taskToString(this.tasks.get(i)));
        }
        return data.toString();
    }
}
