package Duke;

import java.util.ArrayList;

/**
 * Contains the task list e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {
    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        if (Storage.updateFile(task)) {
            tasks.add(task);
            Ui.printAddSuccessfulMsg(task, tasks.size());
        }
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public void markTaskAsDone(int i) {
        Task task = tasks.get(i);
        task.markTaskAsDone();
        if (Storage.rewriteEntireFile(tasks)) {
            Ui.printMarkTaskSuccessfulMsg(task);
        }
    }

    public void unMarkTaskAsDone(int i) {
        Task task = tasks.get(i);
        task.unMarkTaskAsDone();
        if (Storage.rewriteEntireFile(tasks)) {
            Ui.printUnMarkTaskSuccessfulMsg(task);
        }
    }

    public void delete(int i) {
        Task task = tasks.get(i);
        tasks.remove(i);
        if (Storage.rewriteEntireFile(tasks)) {
            Ui.printDeleteSuccessfulMsg(task, tasks.size());
        }
    }
}
