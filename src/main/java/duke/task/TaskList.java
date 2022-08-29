package duke.task;

import duke.Ui;

import java.util.ArrayList;

/**
 * Represents a list of tasks
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Assigns input task arraylist to
     * object task arraylist
     */
    public TaskList(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
    }

    /**
     * Returns object task arraylist
     */
    public ArrayList<Task> getTasks() {
        return TaskList.tasks;
    }

    /**
     * Returns a string listing all tasks in Tasklist
     */
    public String listTasks() {
        String tasks = "";
        if (TaskList.tasks.isEmpty()) {
            tasks = "You have no tasks! :D";
        } else {
            for (int i = 0; i < TaskList.tasks.size(); i++) {
                tasks += (i + 1) + "." + TaskList.tasks.get(i) + "\n";
            }
        }
        return tasks;
    }

    /**
     * Marks a task in task arraylist as
     * done/not done
     */
    public void markTask(boolean isDone, int index) {
        TaskList.tasks.get(index).isDone = isDone;
        Ui.markLog(TaskList.tasks.get(index), isDone);
    }

    /**
     * Adds a task to task arraylist
     */
    public void addTask(Task task) {
        TaskList.tasks.add(task);
        Ui.addTaskLog(task);
    }

    /**
     * Removes a task from task arraylist
     */
    public void removeTask(int index) {
        Ui.removeTaskLog(TaskList.tasks.get(index));
        TaskList.tasks.remove(index);
    }

    /**
     * Returns a string containing tasks that have been found
     * after searching using text
     */
    public String findTask(String text) {
        String tasks = "";
        int counter = 0;

        for (int i = 0; i < TaskList.tasks.size(); i++) {
            if (TaskList.tasks.get(i).getDescription().contains(text)) {
                tasks += (counter + 1) + "." + TaskList.tasks.get(i) + "\n";
                counter++;
            }
        }

        if (counter > 0) {
            return tasks.trim();
        }

        return "No tasks found!";
    }

    /**
     * Returns the number of tasks in
     * task arraylist
     */
    public static int length() {
        return TaskList.tasks.size();
    }
}
