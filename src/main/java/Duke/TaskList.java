package Duke;

import java.util.ArrayList;

/**
 * The class contains the task list e.g., it has operations to add/delete tasks in the list
 * @author LimWeiJun
 */
public class TaskList {
    ArrayList<Task> tasks;
    Ui ui;
    MainWindow mainWindow;
    Storage storage;

    /**
     * The method takes in two parameter
     * @param ui of type Ui
     * @param storage of type Ui
     */
    public TaskList(Ui ui, Storage storage, MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.tasks = storage.load();
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * The method takes in a parameter
     * @param task of type Task
     */
    public void add(Task task) {
        if (storage.updateFile(task)) {
            tasks.add(task);
            mainWindow.printAddSuccessfulMsg(task, tasks.size());
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

    /**
     * The method takes in a parameter of type int
     * @param i of type int
     */
    public void delete(int i) {
        Task task = tasks.get(i);
        tasks.remove(i);
        if (storage.rewriteEntireFile(tasks)) {
            mainWindow.printDeleteSuccessfulMsg(task, tasks.size());
        }
    }
}
