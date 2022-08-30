package Duke.DukeHandlers;

import Duke.DukeHandlers.Storage;
import Duke.DukeTasks.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class TasksManager {
    private Storage storage;
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Creates a TasksManager class.
     *
     * @return A TasksManager.
     */
    public TasksManager() {
        this.storage = new Storage();
        this.storage.readfile(this);
    }

    /**
     * Adds a Task and update Storage.
     *
     * @param task A Task.
     * @return A String.
     */
    public String addTask(Task task) {
        //add to the tasks
        tasks.add(task);
        //write to file
        storage.addTask(task);
        //return string
        String ret = "Got it. I've added this task: " + task + "\n Now you have "
                + numTasks() + " tasks in your list";

        return ret;
    }

    /**
     * Adds a task to the tasks ArrayList without
     * adding it to Storage.
     *
     * @param task A Task to be added.
     * @return void.
     */
    public void addTaskNoPrint(Task task) {
        tasks.add(task);
    }

    /**
     * Prints every item in the tasks Arraylist
     * to the console.
     *
     * @return A String with all items from tasklist.
     */
    public String showList() {
        String ret = "Here are the tasks in your list: \n";
        for (int i = 0; i < tasks.size(); i++) {
            int counter = i + 1;
            ret = ret + counter + ". " + tasks.get(i) + "\n";
        }
        return ret;
    }

    /**
     * Marks a specific task as done.
     *
     * @param n the id of the task.
     * @return A String.
     */
    public String markTaskAsDone(int n) {
        Task doneTask = this.tasks.get(n - 1);
        doneTask.markAsDone();

        // rewrite file entirely
        storage.rewriteFile(this.tasks);
        //return string
        String ret = "Nice! I've marked this task as done:\n" + doneTask;
        return ret;
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param n the id of the task to be deleted.
     * @return A String showing deleted task.
     */
    public String deleteTask(int n) {
        Task deleted = this.tasks.remove(n - 1);
        //rewrite file entirely
        storage.rewriteFile(this.tasks);
        //return string
        String ret = "The following task has been deleted:\n" + deleted;
        return ret;
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param match the String to search for.
     * @return A String showing all matched.
     */
    public String find(String match) {
        String ret = "The following tasks match your search:\n";

        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.getMatching(match)) {
                ret += currTask;
            }
        }
        return ret;
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @return An int showing number of tasks.
     */
    public int numTasks() {
        return tasks.size();
    }

    /**
     * Tells storage that user session is ending.
     *
     * @return void.
     */
    public void closePW() {
        storage.end();
    }

    /**
     * Prints out all matching dates to the console.
     *
     * @param localDate the LocalDate the user wants.
     * @return A String representing LocalDate.
     */
    public String showDate(LocalDate localDate) {
        String ret = "These tasks are due on " + localDate + "\n";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).compareDate(localDate)) {
                ret += tasks.get(i) + "\n";
            }
        }
        return ret;
    }
}
