package Duke;

import java.util.ArrayList;

/**
 * The class contains the task list e.g., it has operations to add/delete tasks in the list
 * @author LimWeiJun
 */
public class TaskList {
    ArrayList<Task> tasks;
    MainWindow mainWindow;
    Storage storage;

    /**
     * The method takes in two parameter
     * @param storage of type Storage
     */
    public TaskList(Storage storage, MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.tasks = storage.load();

        this.storage = storage;
    }

    /**
     * The method takes in a parameter
     * @param task of type Task
     */
    public void addTask(Task task) {
        if (storage.updateFile(task)) {
            tasks.add(task);
            mainWindow.printAddSuccessfulMsg(task, tasks.size());
        }
    }

    /**
     * The method takes in a parameter of type int
     * @param i of type int
     */
    public void updateTask(int i, String newDateStr) {
        Task task = tasks.get(i);
        task.updateDateTime(newDateStr);
        if (storage.rewriteEntireFile(tasks)) {
            mainWindow.printUpdateSuccessfulMsg(task);
        }
    }

    /**
     * The method takes in a parameter of type int
     * @param i of type int
     */
    public void deleteTask(int i) {
        Task task = tasks.get(i);
        tasks.remove(i);
        if (storage.rewriteEntireFile(tasks)) {
            mainWindow.printDeleteSuccessfulMsg(task, tasks.size());
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
        if (storage.rewriteEntireFile(tasks)) {
            mainWindow.printMarkTaskSuccessfulMsg(task);
        }
    }

    /**
     * The method takes in a parameter of type int
     * @param i of type int
     */
    public void unMarkTaskAsDone(int i) {
        Task task = tasks.get(i);
        task.setTaskAsUnDone();
        if (storage.rewriteEntireFile(tasks)) {
            mainWindow.printUnMarkTaskSuccessfulMsg(task);
        }
    }
}
