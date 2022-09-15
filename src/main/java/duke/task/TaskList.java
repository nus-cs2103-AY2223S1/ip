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
     * Returns a string listing all tasks in Tasklist
     */
    public String listTasks() {
        String tasks = "";
        if (TaskList.tasks.isEmpty()) {
            tasks = "You have no tasks! :D";
        } else {
            for (int i = 0; i < TaskList.tasks.size(); i++) {
                tasks += formatTaskForPrint(i + 1, TaskList.tasks.get(i));
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
     * Edits the description of the task at input index
     */
    public void editDesc(int index, String description) {
        Task task = TaskList.tasks.get(index);
        task.editDescription(description);
        Ui.editDescTaskLog(index, task);
    }

    /**
     * Returns a string containing tasks that have been found
     * after searching using text
     */
    public String findTask(String text) {
        String tasks = "";
        int counter = 0;

        for (int i = 0; i < TaskList.tasks.size(); i++) {
            String description = TaskList.tasks.get(i).getDescription();

            if (description.contains(text)) {
                tasks += formatTaskForPrint(counter + 1, TaskList.tasks.get(i));
                counter++;
            }
        }

        if (counter > 0) {
            return tasks.trim();
        }

        return "No tasks found!";
    }

    /**
     * Returns a string describing a task to print to
     * the user
     */
    private String formatTaskForPrint(int number, Task task) {
        return number + "." + task + "\n";
    }

    /**
     * Returns the number of tasks in
     * tasks arraylist
     */
    public static int length() {
        return TaskList.tasks.size();
    }
}
