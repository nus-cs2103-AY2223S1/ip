package duke.task;

import java.util.ArrayList;
import duke.Ui;

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
     * Prints all tasks from task arraylist
     */
    public void printTasks() {
        Ui.separationLine();
        if (TaskList.tasks.isEmpty()) {
            System.out.println("You have no tasks! :D");
        } else {
            for (int i = 0; i < TaskList.tasks.size(); i++) {
                System.out.println((i + 1) + "." + TaskList.tasks.get(i));
            }
        }
        Ui.separationLine();
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
     * Returns the number of tasks in
     * task arraylist
     */
    public static int length() {
        return TaskList.tasks.size();
    }
}
