package Duke;

import java.util.ArrayList;

/**
 * The class contains the task list e.g., it has operations to add/delete tasks in the list
 * @author LimWeiJun
 */
public class TaskList {
    ArrayList<Task> tasks;

    /**
     * The method takes in a parameter
     * @param tasks of type ArrayList
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * The method takes in a parameter
     * @param task of type Task
     */
    public void add(Task task) {
        if (Storage.updateFile(task)) {
            tasks.add(task);
            Ui.printAddSuccessfulMsg(task, tasks.size());
        }
    }

    /**
     * The method returns a variable of type int
     * @return of type int
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * The method takes in a parameter and returns a variable of type int
     * @param i of type int
     * @return of type int
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * The method takes in a parameter of type int
     * @param i of type int
     */
    public void markTaskAsDone(int i) {
        Task task = tasks.get(i);
        task.setTaskAsDone();
        if (Storage.rewriteEntireFile(tasks)) {
            Ui.printMarkTaskSuccessfulMsg(task);
        }
    }

    /**
     * The method takes in a parameter of type int
     * @param i of type int
     */
    public void unMarkTaskAsDone(int i) {
        Task task = tasks.get(i);
        task.setTaskAsUnDone();
        if (Storage.rewriteEntireFile(tasks)) {
            Ui.printUnMarkTaskSuccessfulMsg(task);
        }
    }

    /**
     * The method takes in a parameter of type int
     * @param i of type int
     */
    public void delete(int i) {
        Task task = tasks.get(i);
        tasks.remove(i);
        if (Storage.rewriteEntireFile(tasks)) {
            Ui.printDeleteSuccessfulMsg(task, tasks.size());
        }
    }
}
