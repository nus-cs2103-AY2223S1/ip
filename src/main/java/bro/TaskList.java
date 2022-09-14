package bro;

import java.util.ArrayList;

import bro.task.Task;

/**
 * TaskList class.
 */
public class TaskList {

    protected ArrayList<Task> tasks;
    protected Ui ui = new Ui();

    /**
     * Constructor that initialises tasks variable.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor that initialises tasks variable with the given task.
     */
    public TaskList(ArrayList<Task> task) {
        this.tasks = task;
    }
    /**
     * Returns the size of task.
     * @return The TaskList size.
     */
    public int size() {
        return tasks.size();
    }
    /**
     * Returns the task in the particular index.
     * @param index The index of the task.
     * @return The task at the particular index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Lists all the task in the ArrayList tasks.
     * @return The list of the tasks.
     */
    public String listAll() {
        String result = "Here are the tasks:" + "\n";
        int count = 1;
        if (this.tasks.size() == 0) {
            return "U R FREE! No tasks left.";
        }
        for (Task t : this.tasks) {
            result += count + "." + t.toString() + "\n";
            count++;
        }
        return result;
    }

    /**
     * Marks the task as done by setting isDone boolean to true.
     * @param n Index of the task to be marked.
     */
    public void markTask(int n) throws BroException {
        this.tasks.get(n - 1).markAsDone();
    }

    /**
     * Unmarks the task as done by setting isDone boolean to false.
     * @param n Index of the task to be unmarked.
     */
    public void unmarkTask(int n) {
        this.tasks.get(n - 1).markAsNotDone();
    }

    /**
     * Adds task to the list of the tasks.
     * @param t Task to be added
     */
    public void addTask(Task t) {
        t.markAsNotDone();
        tasks.add(t);
    }
    /**
     * Deletes the task from the task list.
     * @param n Index of the task to be deleted.
     */
    public void deleteTask(int n) {
        tasks.remove(n - 1);
    }

    /**
     * Returns tasks with the given keyword.
     * @param keyword The word which has to be found in the file.
     * @return TaskList which has task with the keyword.
     */
    public TaskList findTask(String keyword) {
        TaskList keywordTask = new TaskList();
        tasks.stream().filter(task -> task.toString().contains(keyword)).forEach(keywordTask::addTask);
        return keywordTask;
    }
}
