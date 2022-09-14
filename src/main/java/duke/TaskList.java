package duke;

import java.util.ArrayList;

/**
 * The class contains the task list e.g., it has operations to add/delete tasks in the list
 *
 * @author LimWeiJun
 */
public class TaskList {
    ArrayList<Task> tasks;
    MainWindow mainWindow;
    Storage storage;

    /**
     * Constructor that takes in object of type Storage and MainWindow
     *
     * @param storage of type Storage
     * @param mainWindow of type MainWindow
     */
    public TaskList(Storage storage, MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.tasks = storage.load();

        this.storage = storage;
    }

    /**
     * Adds task with given object of type Task
     *
     * @param task of type Task
     */
    public void addTask(Task task) {
        if (storage.addFile(task)) {
            tasks.add(task);
            mainWindow.printAddSuccessfulMsg(task, tasks.size());
        }
    }

    /**
     *Updates task with new datetime
     *
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
     * Deletes task with given index i
     *
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
     * Returns the size of the array list
     *
     * @return of type int
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the task with given index i
     *
     * @param i of type int
     * @return of type int
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Marks task as done with given index i
     *
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
     * Unmarks task as done with given index i
     *
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
